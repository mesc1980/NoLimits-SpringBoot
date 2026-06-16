package com.example.NoLimits.dto.producto.request;

import com.example.NoLimits.Multimedia.dto.producto.request.ImagenesRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImagenesRequestDTOTest {

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        @DisplayName("asigna y obtiene propiedades correctamente")
        void testGettersSetters() {
            ImagenesRequestDTO dto = new ImagenesRequestDTO();
            dto.setRuta("/assets/img/Peliculas/spiderman.webp");
            dto.setAltText("Spider-Man posando");
            dto.setProductoId(10L);

            assertEquals("/assets/img/Peliculas/spiderman.webp", dto.getRuta());
            assertEquals("Spider-Man posando", dto.getAltText());
            assertEquals(10L, dto.getProductoId());
        }
    }

    @Nested
    @DisplayName("Valores por defecto")
    class ValoresPorDefecto {

        @Test
        @DisplayName("inicia con campos nulos")
        void testValoresPorDefecto() {
            ImagenesRequestDTO dto = new ImagenesRequestDTO();
            assertNull(dto.getRuta());
            assertNull(dto.getAltText());
            assertNull(dto.getProductoId());
        }
    }

    @Nested
    @DisplayName("Métodos Lombok")
    class MetodosLombok {

        @Test
        @DisplayName("genera equals y hashCode correctamente")
        void testEqualsYHashCode() {
            ImagenesRequestDTO dto1 = new ImagenesRequestDTO();
            dto1.setRuta("/assets/img/Peliculas/spiderman.webp");
            dto1.setAltText("Spider-Man");
            dto1.setProductoId(10L);

            ImagenesRequestDTO dto2 = new ImagenesRequestDTO();
            dto2.setRuta("/assets/img/Peliculas/spiderman.webp");
            dto2.setAltText("Spider-Man");
            dto2.setProductoId(10L);

            assertEquals(dto1, dto2);
            assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        @DisplayName("equals retorna true para misma instancia")
        void equalsMismaInstancia() {
            ImagenesRequestDTO dto = new ImagenesRequestDTO();
            assertEquals(dto, dto);
        }

        @Test
        @DisplayName("equals retorna false con null")
        void equalsConNull() {
            assertNotEquals(null, new ImagenesRequestDTO());
        }

        @Test
        @DisplayName("equals retorna false con otra clase")
        void equalsConOtraClase() {
            assertNotEquals("texto", new ImagenesRequestDTO());
        }

        @Test
        @DisplayName("objetos vacíos son iguales entre sí")
        void objetosVaciosSonIguales() {
            assertEquals(new ImagenesRequestDTO(), new ImagenesRequestDTO());
            assertEquals(new ImagenesRequestDTO().hashCode(), new ImagenesRequestDTO().hashCode());
        }

        @Test
        @DisplayName("equals detecta diferencia en ruta")
        void equalsRutaDistinta() {
            ImagenesRequestDTO dto1 = new ImagenesRequestDTO(); dto1.setRuta("img1.webp");
            ImagenesRequestDTO dto2 = new ImagenesRequestDTO(); dto2.setRuta("img2.webp");
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("equals detecta diferencia en altText")
        void equalsAltTextDistinto() {
            ImagenesRequestDTO dto1 = new ImagenesRequestDTO(); dto1.setAltText("texto A");
            ImagenesRequestDTO dto2 = new ImagenesRequestDTO(); dto2.setAltText("texto B");
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("equals detecta diferencia en productoId")
        void equalsProductoIdDistinto() {
            ImagenesRequestDTO dto1 = new ImagenesRequestDTO(); dto1.setProductoId(1L);
            ImagenesRequestDTO dto2 = new ImagenesRequestDTO(); dto2.setProductoId(2L);
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("null vs valor — ruta")
        void nullVsValorRuta() {
            ImagenesRequestDTO dto1 = new ImagenesRequestDTO();
            ImagenesRequestDTO dto2 = new ImagenesRequestDTO(); dto2.setRuta("img1.webp");
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("null vs valor — altText")
        void nullVsValorAltText() {
            ImagenesRequestDTO dto1 = new ImagenesRequestDTO();
            ImagenesRequestDTO dto2 = new ImagenesRequestDTO(); dto2.setAltText("Spider-Man");
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("null vs valor — productoId")
        void nullVsValorProductoId() {
            ImagenesRequestDTO dto1 = new ImagenesRequestDTO();
            ImagenesRequestDTO dto2 = new ImagenesRequestDTO(); dto2.setProductoId(10L);
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("genera toString correctamente")
        void testToString() {
            ImagenesRequestDTO dto = new ImagenesRequestDTO();
            dto.setRuta("/assets/img/Peliculas/spiderman.webp");
            String resultado = dto.toString();
            assertNotNull(resultado);
            assertTrue(resultado.contains("/assets/img/Peliculas/spiderman.webp"));
        }

        @Test
        @DisplayName("toString objeto vacío no lanza excepción")
        void toStringVacio() {
            assertNotNull(new ImagenesRequestDTO().toString());
        }
    }
}