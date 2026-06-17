package com.example.NoLimits.dto.ubicacion.request;

import com.example.NoLimits.Multimedia.dto.ubicacion.request.ComunaRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de ComunaRequestDTO")
class ComunaRequestDTOTest {

    @Nested
    @DisplayName("Getters y Setters")
    class GetterSetterTests {

        @Test
        @DisplayName("Asigna y obtiene valores correctamente")
        void testGettersAndSetters() {
            ComunaRequestDTO dto = new ComunaRequestDTO();

            dto.setNombre("Santiago");
            dto.setRegionId(13L);

            assertEquals("Santiago", dto.getNombre());
            assertEquals(13L, dto.getRegionId());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("Objetos iguales")
        void testEqualsAndHashCode() {
            ComunaRequestDTO dto1 = crearDTO();
            ComunaRequestDTO dto2 = crearDTO();

            assertEquals(dto1, dto2);
            assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        @DisplayName("Objetos diferentes")
        void testNotEquals() {
            ComunaRequestDTO dto1 = crearDTO();

            ComunaRequestDTO dto2 = crearDTO();
            dto2.setNombre("Providencia");

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Comparación con null")
        void testNotEqualsNull() {
            ComunaRequestDTO dto = new ComunaRequestDTO();

            assertNotEquals(null, dto);
        }

        @Test
        @DisplayName("Comparación consigo mismo")
        void testEqualsSameInstance() {
            ComunaRequestDTO dto = new ComunaRequestDTO();

            assertEquals(dto, dto);
        }

        @Test
        @DisplayName("Comparación con otra clase")
        void testNotEqualsDifferentClass() {
            ComunaRequestDTO dto = crearDTO();

            assertNotEquals(dto, "texto");
        }

        @Test
        @DisplayName("Objetos vacíos son iguales")
        void objetosVaciosSonIguales() {
            ComunaRequestDTO dto1 = new ComunaRequestDTO();
            ComunaRequestDTO dto2 = new ComunaRequestDTO();

            assertEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Objetos vacíos tienen mismo hashCode")
        void objetosVaciosHashCode() {
            ComunaRequestDTO dto1 = new ComunaRequestDTO();
            ComunaRequestDTO dto2 = new ComunaRequestDTO();

            assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        @DisplayName("Null vs valor nombre")
        void nullVsValorNombre() {
            ComunaRequestDTO dto1 = new ComunaRequestDTO();

            ComunaRequestDTO dto2 = new ComunaRequestDTO();
            dto2.setNombre("Santiago");

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Valor vs null nombre")
        void valorVsNullNombre() {
            ComunaRequestDTO dto1 = new ComunaRequestDTO();
            dto1.setNombre("Santiago");

            ComunaRequestDTO dto2 = new ComunaRequestDTO();

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Null vs valor regionId")
        void nullVsValorRegionId() {
            ComunaRequestDTO dto1 = new ComunaRequestDTO();

            ComunaRequestDTO dto2 = new ComunaRequestDTO();
            dto2.setRegionId(13L);

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Valor vs null regionId")
        void valorVsNullRegionId() {
            ComunaRequestDTO dto1 = new ComunaRequestDTO();
            dto1.setRegionId(13L);

            ComunaRequestDTO dto2 = new ComunaRequestDTO();

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Diferente nombre")
        void diferenteNombre() {
            ComunaRequestDTO dto1 = crearDTO();

            ComunaRequestDTO dto2 = crearDTO();
            dto2.setNombre("Providencia");

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("Diferente regionId")
        void diferenteRegionId() {
            ComunaRequestDTO dto1 = crearDTO();

            ComunaRequestDTO dto2 = crearDTO();
            dto2.setRegionId(99L);

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("HashCode objeto vacío")
        void hashCodeObjetoVacio() {
            ComunaRequestDTO dto = new ComunaRequestDTO();

            assertNotEquals(0, dto.hashCode());
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTests {

        @Test
        @DisplayName("Genera representación de texto")
        void testToString() {
            ComunaRequestDTO dto = crearDTO();

            String result = dto.toString();

            assertNotNull(result);
            assertTrue(result.contains("Santiago"));
            assertTrue(result.contains("13"));
        }
    }

    private ComunaRequestDTO crearDTO() {
        ComunaRequestDTO dto = new ComunaRequestDTO();
        dto.setNombre("Santiago");
        dto.setRegionId(13L);
        return dto;
    }
}