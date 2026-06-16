package com.example.NoLimits.dto.ubicacion.response;

import com.example.NoLimits.Multimedia.dto.ubicacion.response.ComunaResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de ComunaResponseDTO")
class ComunaResponseDTOTest {

    // ── helper ──────────────────────────────────────────────────────────────
    private ComunaResponseDTO crearDTO() {
        ComunaResponseDTO dto = new ComunaResponseDTO();
        dto.setId(13101L);
        dto.setNombre("Santiago");
        dto.setRegionId(13L);
        dto.setRegionNombre("Región Metropolitana de Santiago");
        return dto;
    }

    // ── Getters / Setters ────────────────────────────────────────────────────
    @Nested @DisplayName("Getters y Setters")
    class GetterSetterTests {

        @Test @DisplayName("Asigna y obtiene todos los valores correctamente")
        void testGettersAndSetters() {
            ComunaResponseDTO dto = new ComunaResponseDTO();
            dto.setId(13101L);
            dto.setNombre("Santiago");
            dto.setRegionId(13L);
            dto.setRegionNombre("Región Metropolitana de Santiago");

            assertEquals(13101L, dto.getId());
            assertEquals("Santiago", dto.getNombre());
            assertEquals(13L, dto.getRegionId());
            assertEquals("Región Metropolitana de Santiago", dto.getRegionNombre());
        }

        @Test @DisplayName("Permite sobreescribir valores")
        void testOverwriteValues() {
            ComunaResponseDTO dto = crearDTO();
            dto.setNombre("Providencia");
            dto.setId(13123L);
            assertEquals("Providencia", dto.getNombre());
            assertEquals(13123L, dto.getId());
        }

        @Test @DisplayName("Acepta valores null en todos los campos")
        void testNullValues() {
            ComunaResponseDTO dto = new ComunaResponseDTO();
            dto.setId(null);
            dto.setNombre(null);
            dto.setRegionId(null);
            dto.setRegionNombre(null);

            assertNull(dto.getId());
            assertNull(dto.getNombre());
            assertNull(dto.getRegionId());
            assertNull(dto.getRegionNombre());
        }

        @Test @DisplayName("Objeto recién construido tiene todos los campos null")
        void testDefaultConstructorNulls() {
            ComunaResponseDTO dto = new ComunaResponseDTO();
            assertNull(dto.getId());
            assertNull(dto.getNombre());
            assertNull(dto.getRegionId());
            assertNull(dto.getRegionNombre());
        }
    }

    // ── Equals / HashCode ────────────────────────────────────────────────────
    @Nested @DisplayName("Equals y HashCode")
    class EqualsHashCodeTests {

        @Test @DisplayName("Objetos con mismos datos son iguales")
        void testEquals() {
            assertEquals(crearDTO(), crearDTO());
        }

        @Test @DisplayName("Mismo hashCode para objetos equivalentes")
        void testHashCode() {
            assertEquals(crearDTO().hashCode(), crearDTO().hashCode());
        }

        @Test @DisplayName("Comparación consigo mismo")
        void testEqualsSameInstance() {
            ComunaResponseDTO dto = crearDTO();
            assertEquals(dto, dto);
        }

        @Test @DisplayName("Comparación con null retorna false")
        void testNotEqualsNull() {
            assertNotEquals(null, crearDTO());
        }

        @Test @DisplayName("Comparación con otra clase retorna false")
        void testNotEqualsOtraClase() {
            assertNotEquals("texto", crearDTO());
        }

        @Test @DisplayName("Diferente por id")
        void testNotEqualsPorId() {
            ComunaResponseDTO d1 = crearDTO(), d2 = crearDTO();
            d2.setId(999L);
            assertNotEquals(d1, d2);
            assertNotEquals(d1.hashCode(), d2.hashCode());
        }

        @Test @DisplayName("Diferente por nombre")
        void testNotEqualsPorNombre() {
            ComunaResponseDTO d1 = crearDTO(), d2 = crearDTO();
            d2.setNombre("Providencia");
            assertNotEquals(d1, d2);
        }

        @Test @DisplayName("Diferente por regionId")
        void testNotEqualsPorRegionId() {
            ComunaResponseDTO d1 = crearDTO(), d2 = crearDTO();
            d2.setRegionId(99L);
            assertNotEquals(d1, d2);
        }

        @Test @DisplayName("Diferente por regionNombre")
        void testNotEqualsPorRegionNombre() {
            ComunaResponseDTO d1 = crearDTO(), d2 = crearDTO();
            d2.setRegionNombre("Valparaíso");
            assertNotEquals(d1, d2);
        }

        @Test @DisplayName("Objetos vacíos son iguales entre sí")
        void testVaciosIguales() {
            assertEquals(new ComunaResponseDTO(), new ComunaResponseDTO());
            assertEquals(new ComunaResponseDTO().hashCode(), new ComunaResponseDTO().hashCode());
        }

        @Test @DisplayName("Con id null vs id con valor son distintos")
        void testNullVsValorId() {
            ComunaResponseDTO d1 = new ComunaResponseDTO();
            ComunaResponseDTO d2 = new ComunaResponseDTO();
            d2.setId(1L);
            assertNotEquals(d1, d2);
        }

        @Test @DisplayName("Con nombre null vs nombre con valor son distintos")
        void testNullVsValorNombre() {
            ComunaResponseDTO d1 = new ComunaResponseDTO();
            ComunaResponseDTO d2 = new ComunaResponseDTO();
            d2.setNombre("Santiago");
            assertNotEquals(d1, d2);
        }
    }

    // ── ToString ─────────────────────────────────────────────────────────────
    @Nested @DisplayName("ToString")
    class ToStringTests {

        @Test @DisplayName("No es null")
        void testNotNull() {
            assertNotNull(crearDTO().toString());
        }

        @Test @DisplayName("Contiene nombre e id")
        void testContenido() {
            String s = crearDTO().toString();
            assertTrue(s.contains("Santiago"));
            assertTrue(s.contains("13101"));
        }

        @Test @DisplayName("Contiene regionId y regionNombre")
        void testContiene_regionId() {
            String s = crearDTO().toString();
            assertTrue(s.contains("13"));
            assertTrue(s.contains("Región Metropolitana de Santiago") || s.contains("regionNombre"));
        }

        @Test @DisplayName("Objeto vacío genera toString sin errores")
        void testVacio() {
            assertNotNull(new ComunaResponseDTO().toString());
        }
    }
}