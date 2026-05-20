package com.example.NoLimits.Multimedia.chatbot.ai;

import com.example.NoLimits.Multimedia.service.ai.ProductoEmbeddingService;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Component
public class OpenAIChatClient {

    private static final Logger log = LoggerFactory.getLogger(OpenAIChatClient.class);

    private OpenAIClient client;

    @Value("${openai.api-key}")
    private String openAiApiKey;

    private final ProductoEmbeddingService productoEmbeddingService;

    public OpenAIChatClient(ProductoEmbeddingService productoEmbeddingService) {
        this.productoEmbeddingService = productoEmbeddingService;
    }

    @PostConstruct
    public void init() {
        this.client = OpenAIOkHttpClient.builder()
                .apiKey(openAiApiKey)
                .build();
    }

    public String askNoLimits(String userMessage) {

        List<String> resultadosBD = productoEmbeddingService.buscarSimilares(userMessage, 10);

        String contextoBD = resultadosBD.isEmpty()
                ? "SIN_RESULTADOS"
                : String.join("\n---\n", resultadosBD);

        String systemPrompt = """
                Eres el asistente oficial de NoLimits, una plataforma de contenido multimedia.
                NoLimits ofrece: Películas, Series, Videojuegos, Anime, Música y Libros.
                Responde SIEMPRE en español, de forma clara, amable y formal usando "usted".
                No uses Markdown, asteriscos, ni símbolos de formato. Solo texto plano.

                IDENTIDAD:
                - Siempre llama "título" a cualquier contenido (película, serie, anime, videojuego, libro, música).
                - Nunca uses "juego" para referirte a una película, serie o anime.
                - Nunca uses "show", "programa" u otros términos en inglés o informales.

                REGLAS DE RESPUESTA:
                1. Solo habla de títulos que aparezcan en la sección "Información disponible". Nunca inventes títulos, ratings, plataformas ni precios.
                2. Si la información disponible es "SIN_RESULTADOS" o no contiene nada relacionado con la pregunta, responde siempre algo como: "No cuento con información sobre ese título en este momento. Sin embargo, NoLimits tiene un catálogo muy amplio y es muy probable que lo encuentre. Le invitamos a buscarlo usando el buscador de la plataforma."
                3. Si el usuario pregunta por una saga, personaje o franquicia (ej: Naruto, Harry Potter, Star Wars), lista TODOS los títulos relacionados que aparezcan en la información disponible.
                4. Si el usuario pregunta cómo ver, encontrar o acceder a un título, responde siempre: "Puede buscarlo en el buscador de NoLimits escribiendo el nombre del título." Si además conoces una plataforma de streaming real (Netflix, Crunchyroll, Disney+, etc.) para ese título, menciónala como sugerencia adicional.
                5. Si el usuario pregunta por un título específico, responde SOLO sobre ese título. No menciones otros títulos no relacionados.
                6. Sé breve: máximo 4 líneas por título. Si hay varios títulos, lista cada uno con su descripción breve.
                7. Si el usuario saluda o hace preguntas generales sobre NoLimits, responde de forma amable explicando qué es la plataforma y cómo puede ayudarle.
                8. Si el usuario insulta o escribe algo inapropiado, responde con cortesía indicando que solo puedes ayudar con contenido de NoLimits.
                """;

        try {
            log.info("Llamando a OpenAI con mensaje: {}", userMessage);

            ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                    .model(ChatModel.GPT_4O_MINI)
                    .addSystemMessage(systemPrompt)
                    .addUserMessage("""
                            Información disponible en NoLimits:
                            %s

                            Pregunta del usuario:
                            %s
                            """.formatted(contextoBD, userMessage))
                    .build();

            ChatCompletion completion = client.chat().completions().create(params);

            String texto = completion.choices().get(0).message().content()
                    .orElse("No pude generar una respuesta en este momento.");

            log.info("Respuesta OpenAI recibida correctamente");
            return limpiarTexto(texto);

        } catch (Exception e) {
            log.error("ERROR EN OPENAI: {} - {}", e.getClass().getName(), e.getMessage());
            return "Lo siento, el asistente no está disponible en este momento. Puede usar el buscador de NoLimits directamente.";
        }
    }

    private String limpiarTexto(String texto) {
        return texto
                .replaceAll("(?m)^\\*\\s*", "")
                .replaceAll("\\*+", "")
                .replaceAll("#+", "")
                .replaceAll("\\n{3,}", "\n\n")
                .trim();
    }
}