package com.example.NoLimits.service.chatbot;

import com.example.NoLimits.Multimedia.chatbot.ai.OpenAIChatClient;
import com.example.NoLimits.Multimedia.chatbot.dto.ChatResponse;
import com.example.NoLimits.Multimedia.chatbot.service.ChatbotServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ChatbotServiceTest {

    private OpenAIChatClient openAIClientMock;
    private ChatbotServiceImpl service;

    @BeforeEach
    void setUp() {
        openAIClientMock = mock(OpenAIChatClient.class);
        service = new ChatbotServiceImpl(openAIClientMock);
    }

    @Nested
    @DisplayName("getWelcomeMessage")
    class GetWelcomeMessage {

        @Test
        @DisplayName("retorna un ChatResponse con source 'system'")
        void retornaRespuestaSistema() {
            ChatResponse response = service.getWelcomeMessage();

            assertThat(response).isNotNull();
            assertThat(response.getSource()).isEqualTo("system");
            assertThat(response.getReply()).isNotBlank();
            assertThat(response.getActions()).isNotEmpty();
            assertThat(response.getRoute()).isEqualTo("/principal");
        }
    }

    @Nested
    @DisplayName("processMessage - respuestas por reglas")
    class ProcessMessage {

        @Test
        @DisplayName("detecta 'iniciar sesion' y responde con rule")
        void detectaIniciarSesion() {
            ChatResponse response = service.processMessage("iniciar sesion");
            assertThat(response.getSource()).isEqualTo("rule");
            assertThat(response.getReply()).containsIgnoringCase("login");
        }

        @Test
        @DisplayName("detecta 'crear cuenta' y responde con rule")
        void detectaCrearCuenta() {
            ChatResponse response = service.processMessage("crear cuenta");
            assertThat(response.getSource()).isEqualTo("rule");
            assertThat(response.getReply()).isNotBlank();
        }

        @Test
        @DisplayName("detecta 'cerrar sesion' y responde con rule")
        void detectaCerrarSesion() {
            ChatResponse response = service.processMessage("cerrar sesion");
            assertThat(response.getSource()).isEqualTo("rule");
            assertThat(response.getReply()).containsIgnoringCase("logout");
        }

        @Test
        @DisplayName("detecta 'favoritos' y responde con rule")
        void detectaFavoritos() {
            ChatResponse response = service.processMessage("mis favoritos");
            assertThat(response.getSource()).isEqualTo("rule");
            assertThat(response.getReply()).isNotBlank();
        }

        @Test
        @DisplayName("mensaje desconocido delega en OpenAI (askNoLimits)")
        void mensajeDesconocidoUsaOpenAI() {
            when(openAIClientMock.askNoLimits(anyString()))
                    .thenReturn("Respuesta de la IA");

            ChatResponse response = service.processMessage("xyz987 pregunta muy rara");

            // No matchea ninguna regla → delega en OpenAI
            assertThat(response).isNotNull();
            verify(openAIClientMock, atLeastOnce()).askNoLimits(anyString());
        }
    }
}