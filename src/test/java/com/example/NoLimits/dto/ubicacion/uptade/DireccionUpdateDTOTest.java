package com.example.NoLimits.dto.ubicacion.uptade;

import com.example.NoLimits.Multimedia.dto.ubicacion.update.DireccionUpdateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de DireccionUpdateDTO")
class DireccionUpdateDTOTest {

    @Nested
    @DisplayName("Getters y Setters")
    class GetterSetterTests {

        @Test
        @DisplayName("Asigna y obtiene valores correctamente")
        void testGettersAndSetters() {

            // Arrange
            DireccionUpdateDTO dto = new DireccionUpdateDTO();

            // Act
            dto.setCalle("Av. Providencia");
            dto.setNumero("1234");
            dto.setComplemento("Depto 402");
            dto.setCodigoPostal("7500000");
            dto.setComunaId(13114L);
            dto.setActivo(true);
            dto.setUsuarioId(5L);

            // Assert
            assertEquals("Av. Providencia", dto.getCalle());
            assertEquals("1234", dto.getNumero());
            assertEquals("Depto 402", dto.getComplemento());
            assertEquals("7500000", dto.getCodigoPostal());
            assertEquals(13114L, dto.getComunaId());
            assertTrue(dto.getActivo());
            assertEquals(5L, dto.getUsuarioId());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("Objetos iguales")
        void testEqualsAndHashCode() {

            // Arrange
            DireccionUpdateDTO dto1 = crearDTO();
            DireccionUpdateDTO dto2 = crearDTO();

            // Assert
            assertEquals(dto1, dto2);
            assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        @DisplayName("Objetos diferentes")
        void testNotEquals() {

            // Arrange
            DireccionUpdateDTO dto1 = crearDTO();

            DireccionUpdateDTO dto2 = crearDTO();
            dto2.setNumero("9999");

            // Assert
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Comparación con null")
        void testNotEqualsNull() {

            // Arrange
            DireccionUpdateDTO dto = crearDTO();

            // Assert
            assertNotEquals(null, dto);
        }

        @Test
        @DisplayName("Comparación consigo mismo")
        void testEqualsSameInstance() {

            // Arrange
            DireccionUpdateDTO dto = crearDTO();

            // Assert
            assertEquals(dto, dto);
        }

        @Test
        @DisplayName("Objetos diferentes por calle")
        void testNotEqualsPorCalle() {

            DireccionUpdateDTO dto1 = crearDTO();
            DireccionUpdateDTO dto2 = crearDTO();

            dto2.setCalle("Los Leones");

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Objetos diferentes por complemento")
        void testNotEqualsPorComplemento() {

            DireccionUpdateDTO dto1 = crearDTO();
            DireccionUpdateDTO dto2 = crearDTO();

            dto2.setComplemento("Casa B");

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Objetos diferentes por codigoPostal")
        void testNotEqualsPorCodigoPostal() {

            DireccionUpdateDTO dto1 = crearDTO();
            DireccionUpdateDTO dto2 = crearDTO();

            dto2.setCodigoPostal("8320000");

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Objetos diferentes por comunaId")
        void testNotEqualsPorComunaId() {

            DireccionUpdateDTO dto1 = crearDTO();
            DireccionUpdateDTO dto2 = crearDTO();

            dto2.setComunaId(999L);

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Objetos diferentes por activo")
        void testNotEqualsPorActivo() {

            DireccionUpdateDTO dto1 = crearDTO();
            DireccionUpdateDTO dto2 = crearDTO();

            dto2.setActivo(false);

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Objetos diferentes por usuarioId")
        void testNotEqualsPorUsuarioId() {

            DireccionUpdateDTO dto1 = crearDTO();
            DireccionUpdateDTO dto2 = crearDTO();

            dto2.setUsuarioId(99L);

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Comparación con otra clase")
        void testNotEqualsOtraClase() {

            DireccionUpdateDTO dto = crearDTO();

            assertNotEquals(dto, "texto");
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTests {

        @Test
        @DisplayName("Genera representación textual")
        void testToString() {

            // Arrange
            DireccionUpdateDTO dto = crearDTO();

            // Act
            String result = dto.toString();

            // Assert
            assertNotNull(result);
            assertTrue(result.contains("Av. Providencia"));
            assertTrue(result.contains("1234"));
            assertTrue(result.contains("7500000"));
        }
    }

    private DireccionUpdateDTO crearDTO() {

        DireccionUpdateDTO dto = new DireccionUpdateDTO();
        dto.setCalle("Av. Providencia");
        dto.setNumero("1234");
        dto.setComplemento("Depto 402");
        dto.setCodigoPostal("7500000");
        dto.setComunaId(13114L);
        dto.setActivo(true);
        dto.setUsuarioId(5L);

        return dto;
    }
}