package com.example.NoLimits.dto.ubicacion.response;

import com.example.NoLimits.Multimedia.dto.ubicacion.response.RegionResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de RegionResponseDTO")
class RegionResponseDTOTest {

    private RegionResponseDTO crearDTO() {
        RegionResponseDTO dto = new RegionResponseDTO();
        dto.setId(13L);
        dto.setNombre("Región Metropolitana de Santiago");
        return dto;
    }

    // ── Getters / Setters ────────────────────────────────────────────────────
    @Nested @DisplayName("Getters y Setters")
    class GetterSetterTests {

        @Test @DisplayName("Asigna y obtiene los valores correctamente")
        void testGettersAndSetters() {
            RegionResponseDTO dto = crearDTO();
            assertEquals(13L, dto.getId());
            assertEquals("Región Metropolitana de Santiago", dto.getNombre());
        }

        @Test @DisplayName("Objeto recién construido tiene campos null")
        void testDefaultNulls() {
            RegionResponseDTO dto = new RegionResponseDTO();
            assertNull(dto.getId());
            assertNull(dto.getNombre());
        }

        @Test @DisplayName("Permite sobreescribir id y nombre")
        void testOverwrite() {
            RegionResponseDTO dto = crearDTO();
            dto.setId(5L);
            dto.setNombre("Biobío");
            assertEquals(5L, dto.getId());
            assertEquals("Biobío", dto.getNombre());
        }

        @Test @DisplayName("Acepta valores null")
        void testNullValues() {
            RegionResponseDTO dto = crearDTO();
            dto.setId(null);
            dto.setNombre(null);
            assertNull(dto.getId());
            assertNull(dto.getNombre());
        }
    }

    // ── Equals / HashCode ────────────────────────────────────────────────────
    @Nested @DisplayName("Equals y HashCode")
    class EqualsHashCodeTests {

        @Test @DisplayName("Objetos equivalentes son iguales")
        void testEquals() {
            assertEquals(crearDTO(), crearDTO());
        }

        @Test @DisplayName("Mismo hashCode para objetos equivalentes")
        void testHashCode() {
            assertEquals(crearDTO().hashCode(), crearDTO().hashCode());
        }

        @Test @DisplayName("Comparación consigo mismo")
        void testSameInstance() {
            RegionResponseDTO dto = crearDTO();
            assertEquals(dto, dto);
        }

        @Test @DisplayName("Comparación con null retorna false")
        void testEqualsNull() {
            assertNotEquals(null, crearDTO());
        }

        @Test @DisplayName("Comparación con otra clase retorna false")
        void testEqualsOtraClase() {
            assertNotEquals("texto", crearDTO());
        }

        @Test @DisplayName("Diferente por id")
        void testNotEqualsPorId() {
            RegionResponseDTO d1 = crearDTO(), d2 = crearDTO();
            d2.setId(99L);
            assertNotEquals(d1, d2);
            assertNotEquals(d1.hashCode(), d2.hashCode());
        }

        @Test @DisplayName("Diferente por nombre")
        void testNotEqualsPorNombre() {
            RegionResponseDTO d1 = crearDTO(), d2 = crearDTO();
            d2.setNombre("Valparaíso");
            assertNotEquals(d1, d2);
        }

        @Test @DisplayName("Objetos vacíos son iguales entre sí")
        void testVaciosIguales() {
            assertEquals(new RegionResponseDTO(), new RegionResponseDTO());
            assertEquals(new RegionResponseDTO().hashCode(), new RegionResponseDTO().hashCode());
        }

        @Test @DisplayName("Null vs valor — id")
        void testNullVsValorId() {
            RegionResponseDTO d1 = new RegionResponseDTO();
            RegionResponseDTO d2 = new RegionResponseDTO();
            d2.setId(1L);
            assertNotEquals(d1, d2);
        }

        @Test @DisplayName("Null vs valor — nombre")
        void testNullVsValorNombre() {
            RegionResponseDTO d1 = new RegionResponseDTO();
            RegionResponseDTO d2 = new RegionResponseDTO();
            d2.setNombre("Atacama");
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

        @Test @DisplayName("Contiene id y nombre")
        void testContenido() {
            String s = crearDTO().toString();
            assertTrue(s.contains("13"));
            assertTrue(s.contains("Región Metropolitana de Santiago") || s.contains("nombre"));
        }

        @Test @DisplayName("Objeto vacío genera toString sin errores")
        void testVacio() {
            assertNotNull(new RegionResponseDTO().toString());
        }
    }
}