package com.example.NoLimits.service.scraping;

import com.example.NoLimits.Multimedia.service.scraping.ScrapingClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class SteamScrapingServiceTest {

    private ScrapingClientService buildServiceWithMock(RestTemplate mock) throws Exception {
        ScrapingClientService service = new ScrapingClientService();
        Field field = ScrapingClientService.class.getDeclaredField("restTemplate");
        field.setAccessible(true);
        field.set(service, mock);
        return service;
    }

    @Nested
    @DisplayName("obtenerPrecioSteam - múltiples escenarios")
    class ObtenerPrecioSteam {

        @Test
        @DisplayName("retorna los campos esperados para un juego gratuito")
        void retornaJuegoGratuito() throws Exception {
            RestTemplate mock = mock(RestTemplate.class);
            ScrapingClientService service = buildServiceWithMock(mock);

            Map<String, Object> respuesta = Map.of(
                    "nombre", "Counter-Strike 2",
                    "precio", 0,
                    "precioFormato", "Free To Play",
                    "moneda", "CLP",
                    "plataforma", "Steam"
            );
            when(mock.getForObject(contains("appId=730"), eq(Map.class))).thenReturn(respuesta);

            Map<String, Object> resultado = service.obtenerPrecioSteam("730");

            assertThat(resultado).isNotNull();
            assertThat(resultado.get("precioFormato")).isEqualTo("Free To Play");
            assertThat((int) resultado.get("precio")).isZero();
        }

        @Test
        @DisplayName("retorna los campos esperados para un juego de pago")
        void retornaJuegoDePago() throws Exception {
            RestTemplate mock = mock(RestTemplate.class);
            ScrapingClientService service = buildServiceWithMock(mock);

            Map<String, Object> respuesta = Map.of(
                    "nombre", "Elden Ring",
                    "precio", 59990,
                    "precioFormato", "$59.990 CLP",
                    "moneda", "CLP",
                    "plataforma", "Steam"
            );
            when(mock.getForObject(contains("appId=1245620"), eq(Map.class))).thenReturn(respuesta);

            Map<String, Object> resultado = service.obtenerPrecioSteam("1245620");

            assertThat(resultado.get("nombre")).isEqualTo("Elden Ring");
            assertThat((int) resultado.get("precio")).isEqualTo(59990);
        }

        @Test
        @DisplayName("propaga excepción si el microservicio no está disponible")
        void propagaExcepcionSiMicroservicioFalla() throws Exception {
            RestTemplate mock = mock(RestTemplate.class);
            ScrapingClientService service = buildServiceWithMock(mock);

            when(mock.getForObject(anyString(), eq(Map.class)))
                    .thenThrow(new org.springframework.web.client.ResourceAccessException("Connection refused"));

            assertThatThrownBy(() -> service.obtenerPrecioSteam("730"))
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("verifica que se llama exactamente una vez al microservicio")
        void llamaUnaVezAlMicroservicio() throws Exception {
            RestTemplate mock = mock(RestTemplate.class);
            ScrapingClientService service = buildServiceWithMock(mock);

            when(mock.getForObject(anyString(), eq(Map.class))).thenReturn(Map.of("nombre", "Test Game"));

            service.obtenerPrecioSteam("12345");

            verify(mock, times(1)).getForObject(anyString(), eq(Map.class));
        }
    }
}