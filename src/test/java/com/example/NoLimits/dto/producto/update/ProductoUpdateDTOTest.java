package com.example.NoLimits.dto.producto.update;

import com.example.NoLimits.Multimedia.dto.producto.request.LinkCompraDTO;
import com.example.NoLimits.Multimedia.dto.producto.update.ProductoUpdateDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductoUpdateDTO - Cobertura de ramas equals/hashCode")
class ProductoUpdateDTOBranchTest {

    private LinkCompraDTO link(Long plataformaId, String url) {
        LinkCompraDTO l = new LinkCompraDTO();
        l.setPlataformaId(plataformaId);
        l.setUrl(url);
        return l;
    }

    private ProductoUpdateDTO full() {
        ProductoUpdateDTO dto = new ProductoUpdateDTO();
        dto.setNombre("Spider-Man Remaster");
        dto.setPrecio(14990.0);
        dto.setSinopsis("Un joven héroe descubre sus poderes.");
        dto.setUrlTrailer("https://www.youtube.com/watch?v=test");
        dto.setAnio(2011);
        dto.setTipoProductoId(2L);
        dto.setClasificacionId(3L);
        dto.setEstadoId(1L);
        dto.setTipoEmpresaId(10L);
        dto.setTipoDesarrolladorId(20L);
        dto.setImagenesRutas(List.of("https://.../img1.webp"));
        dto.setLinksCompra(List.of(link(1L, "https://store.steampowered.com/app/test")));
        dto.setSaga("El Señor de los Anillos");
        dto.setPortadaSaga("/assets/img/sagas/lotrSaga.webp");
        dto.setPlataformasIds(List.of(1L, 2L));
        dto.setGenerosIds(List.of(3L, 4L));
        dto.setEmpresasIds(List.of(5L));
        dto.setDesarrolladoresIds(List.of(7L, 8L));
        return dto;
    }

    /**
     * Instancia con todos los campos distintos a full(), del mismo tipo.
     */
    private ProductoUpdateDTO fullAlternativo() {
        ProductoUpdateDTO dto = new ProductoUpdateDTO();
        dto.setNombre("Mario Kart 8");
        dto.setPrecio(29990.0);
        dto.setSinopsis("Carreras de karts.");
        dto.setUrlTrailer("https://www.youtube.com/watch?v=otro");
        dto.setAnio(2017);
        dto.setTipoProductoId(99L);
        dto.setClasificacionId(98L);
        dto.setEstadoId(97L);
        dto.setTipoEmpresaId(100L);
        dto.setTipoDesarrolladorId(200L);
        dto.setImagenesRutas(List.of("https://.../otro.webp"));
        dto.setLinksCompra(List.of(link(2L, "https://www.nintendo.com/otro")));
        dto.setSaga("Mario");
        dto.setPortadaSaga("/assets/img/sagas/marioSaga.webp");
        dto.setPlataformasIds(List.of(9L));
        dto.setGenerosIds(List.of(8L));
        dto.setEmpresasIds(List.of(7L));
        dto.setDesarrolladoresIds(List.of(6L));
        return dto;
    }

    @Nested
    @DisplayName("equals - casos generales")
    class EqualsGenerales {

        @Test
        @DisplayName("es igual a sí mismo")
        void igualASiMismo() {
            ProductoUpdateDTO dto = full();
            assertEquals(dto, dto);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {
            ProductoUpdateDTO dto = full();
            assertNotEquals(null, dto);
        }

        @Test
        @DisplayName("no es igual a un objeto de otra clase")
        void noEsIgualAOtraClase() {
            ProductoUpdateDTO dto = full();
            assertNotEquals(dto, "no soy un ProductoUpdateDTO");
        }

        @Test
        @DisplayName("dos instancias completas con los mismos valores son iguales")
        void instanciasCompletasIgualesSonIguales() {
            ProductoUpdateDTO dto1 = full();
            ProductoUpdateDTO dto2 = full();
            assertEquals(dto1, dto2);
            assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        @DisplayName("dos instancias vacías son iguales")
        void instanciasVaciasSonIguales() {
            ProductoUpdateDTO dto1 = new ProductoUpdateDTO();
            ProductoUpdateDTO dto2 = new ProductoUpdateDTO();
            assertEquals(dto1, dto2);
            assertEquals(dto1.hashCode(), dto2.hashCode());
        }
    }

    @Nested
    @DisplayName("equals - diferencias entre instancias")
    class EqualsDiferencias {

        @Test
        @DisplayName("dos instancias completas con todos los campos distintos no son iguales")
        void instanciasCompletamenteDistintasNoSonIguales() {
            ProductoUpdateDTO dto1 = full();
            ProductoUpdateDTO dto2 = fullAlternativo();

            assertNotEquals(dto1, dto2);
            assertNotEquals(dto2, dto1);
        }

        @Test
        @DisplayName("una instancia completa no es igual a una vacía (todos los campos null vs no-null)")
        void instanciaCompletaVsVaciaNoSonIguales() {
            ProductoUpdateDTO completo = full();
            ProductoUpdateDTO vacio = new ProductoUpdateDTO();

            assertNotEquals(completo, vacio);
            assertNotEquals(vacio, completo);
        }

        @Test
        @DisplayName("difiere solo en nombre")
        void difiereSoloEnNombre() {
            ProductoUpdateDTO dto1 = full();
            ProductoUpdateDTO dto2 = full();
            dto2.setNombre("Otro nombre");

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("difiere solo en nombre null vs no null")
        void difiereSoloEnNombreNull() {
            ProductoUpdateDTO dto1 = full();
            ProductoUpdateDTO dto2 = full();
            dto2.setNombre(null);

            assertNotEquals(dto1, dto2);
            assertNotEquals(dto2, dto1);
        }

        @Test
        @DisplayName("difiere solo en precio")
        void difiereSoloEnPrecio() {
            ProductoUpdateDTO dto1 = full();
            ProductoUpdateDTO dto2 = full();
            dto2.setPrecio(1.0);

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("difiere solo en anio")
        void difiereSoloEnAnio() {
            ProductoUpdateDTO dto1 = full();
            ProductoUpdateDTO dto2 = full();
            dto2.setAnio(1999);

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("difiere solo en linksCompra")
        void difiereSoloEnLinksCompra() {
            ProductoUpdateDTO dto1 = full();
            ProductoUpdateDTO dto2 = full();
            dto2.setLinksCompra(List.of(link(99L, "https://otro.com")));

            assertNotEquals(dto1, dto2);
        }
    }

    @Nested
    @DisplayName("hashCode")
    class HashCodeTest {

        @Test
        @DisplayName("hashCode es consistente entre llamadas")
        void hashCodeConsistente() {
            ProductoUpdateDTO dto = full();
            assertEquals(dto.hashCode(), dto.hashCode());
        }

        @Test
        @DisplayName("hashCode difiere cuando los valores difieren")
        void hashCodeDifiereConValoresDistintos() {
            ProductoUpdateDTO dto1 = full();
            ProductoUpdateDTO dto2 = fullAlternativo();
            assertNotEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        @DisplayName("hashCode de instancia vacía no lanza excepción")
        void hashCodeDeInstanciaVacia() {
            ProductoUpdateDTO dto = new ProductoUpdateDTO();
            assertDoesNotThrow(dto::hashCode);
        }
    }

    @Nested
    @DisplayName("toString")
    class ToStringTest {

        @Test
        @DisplayName("toString de instancia completa contiene los valores de los campos")
        void toStringContieneValores() {
            ProductoUpdateDTO dto = full();
            String resultado = dto.toString();

            assertNotNull(resultado);
            assertTrue(resultado.contains("nombre"));
            assertTrue(resultado.contains("Spider-Man Remaster"));
        }

        @Test
        @DisplayName("toString de instancia vacía no lanza excepción")
        void toStringDeInstanciaVacia() {
            ProductoUpdateDTO dto = new ProductoUpdateDTO();
            assertDoesNotThrow(dto::toString);
        }
    }
}