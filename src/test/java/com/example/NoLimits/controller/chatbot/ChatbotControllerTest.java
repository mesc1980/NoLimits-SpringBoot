package com.example.NoLimits.controller.chatbot;

import com.example.NoLimits.Multimedia.chatbot.controller.ChatbotController;
import com.example.NoLimits.Multimedia.chatbot.dto.ChatResponse;
import com.example.NoLimits.Multimedia.chatbot.service.ChatbotService;
import com.example.NoLimits.config.AbstractContainerBaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ChatbotControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ChatbotService chatbotService;

    private ChatResponse chatResponseEjemplo() {
        return new ChatResponse(
                "¡Hola! Soy el asistente de NoLimits.",
                List.of("¿Qué es NoLimits?", "¿Cómo crear una cuenta?"),
                "/principal",
                false,
                "system"
        );
    }

    @Nested
    @DisplayName("GET /api/chatbot/welcome")
    class Welcome {

        @Test
        @DisplayName("retorna 200 y el mensaje de bienvenida")
        void retornaMensajeBienvenida() throws Exception {
            when(chatbotService.getWelcomeMessage()).thenReturn(chatResponseEjemplo());

            mockMvc.perform(get("/api/chatbot/welcome"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.reply").value("¡Hola! Soy el asistente de NoLimits."))
                    .andExpect(jsonPath("$.source").value("system"))
                    .andExpect(jsonPath("$.route").value("/principal"));

            verify(chatbotService).getWelcomeMessage();
        }
    }

    @Nested
    @DisplayName("POST /api/chatbot/chat")
    class Chat {

        @Test
        @DisplayName("retorna 200 con la respuesta procesada")
        void retornaRespuestaProcesada() throws Exception {
            ChatResponse respuesta = new ChatResponse(
                    "Para iniciar sesión haga clic en Login.",
                    List.of("¿Cómo crear una cuenta?"),
                    "/principal",
                    false,
                    "rule"
            );

            when(chatbotService.processMessage("iniciar sesion")).thenReturn(respuesta);

            String body = "{\"message\":\"iniciar sesion\"}";

            mockMvc.perform(post("/api/chatbot/chat")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.reply").value("Para iniciar sesión haga clic en Login."))
                    .andExpect(jsonPath("$.source").value("rule"));

            verify(chatbotService).processMessage("iniciar sesion");
        }

        @Test
        @DisplayName("retorna 200 aunque el mensaje esté vacío")
        void retornaRespuestaConMensajeVacio() throws Exception {
            ChatResponse respuesta = new ChatResponse(
                    "No entendí tu pregunta.", List.of(), "/principal", false, "rule"
            );

            when(chatbotService.processMessage("")).thenReturn(respuesta);

            mockMvc.perform(post("/api/chatbot/chat")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"message\":\"\"}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.reply").value("No entendí tu pregunta."));
        }
    }
}