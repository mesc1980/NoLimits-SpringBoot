package com.example.NoLimits.Multimedia.chatbot.ai;

import com.example.NoLimits.Multimedia.service.ai.ProductoEmbeddingService;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenAIChatClient {

    private OpenAIClient client;
    private final ProductoEmbeddingService productoEmbeddingService;

    public OpenAIChatClient(ProductoEmbeddingService productoEmbeddingService) {
        this.productoEmbeddingService = productoEmbeddingService;
    }

    @PostConstruct
    public void init() {
        this.client = OpenAIOkHttpClient.fromEnv();
    }

    public String askNoLimits(String userMessage) {

        List<String> resultadosBD = productoEmbeddingService.buscarSimilares(userMessage);

        String contextoBD = resultadosBD.isEmpty()
                ? "SIN_RESULTADOS"
                : String.join("\n", resultadosBD);

        String[] estilos = {
                "Hoy usa un estilo energético, pero sin frases en mayúsculas.",
                "Hoy usa un estilo impaciente cómico, pero sin repetir frases hechas.",
                "Hoy usa un estilo competitivo y directo, sin usar frases repetitivas.",
                "Hoy usa un estilo motivador, breve y claro.",
                "Hoy responde normal, con solo un toque energético al final.",
                "Hoy usa poca paciencia de forma cómica, pero responde con respeto.",
                "Hoy responde rápido y con seguridad, pero sin exagerar la personalidad."
        };

        String estiloDinamico = estilos[(int) (Math.random() * estilos.length)];

        String systemPrompt = """
                Eres el asistente oficial de NoLimits.

                Tu tarea es orientar al usuario dentro de la plataforma.
                Responde siempre en español, de forma clara, amable y útil.

                Contexto real de NoLimits:
                - El usuario entra primero a la página principal.
                - No es obligatorio iniciar sesión para explorar productos.
                - Para iniciar sesión o registrarse, debe usar el menú hamburguesa.
                - Para ver favoritos, primero debe haber iniciado sesión.
                - El usuario puede explorar sagas destacadas o usar el buscador.
                - Desde un producto puede entrar a "Ver Plataformas" y luego "Ver Precios".
                - Al hacer clic sobre un precio, el usuario es redirigido a una plataforma externa.
                - Si preguntan por soporte, el correo es NoLimits@gmail.com.

                NoLimits solo ofrece productos relacionados con: Películas, videojuegos y accesorios.

                Usa la información disponible para responder sobre productos.
                No inventes productos que no estén en la información disponible.
                No menciones términos técnicos como "base de datos", "embeddings" o similares.
                Si la información disponible es "SIN_RESULTADOS", no inventes productos y pregunta al usuario qué busca.

                Tu personalidad está inspirada en Inosuke Hashibira: energético, impulsivo, pero nunca irrespetuoso.
                Dirígete siempre al usuario de forma formal usando "usted".
                Estilo dinámico para esta respuesta: %s

                Reglas de formato:
                - No uses Markdown, ni *, **, #, ##.
                - Responde en texto plano.
                - Si das pasos: 1) paso uno 2) paso dos
                - Usa saltos de línea para separar ideas.
                """.formatted(estiloDinamico);

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model(ChatModel.GPT_4O_MINI)
                .addSystemMessage(systemPrompt)
                .addUserMessage("""
                        Información disponible de NoLimits:
                        %s

                        Pregunta del usuario:
                        %s
                        """.formatted(contextoBD, userMessage))
                .build();

        ChatCompletion completion = client.chat().completions().create(params);

        String texto = completion.choices().get(0).message().content()
                .orElse("No pude generar una respuesta en este momento.");

        return limpiarTexto(texto);
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