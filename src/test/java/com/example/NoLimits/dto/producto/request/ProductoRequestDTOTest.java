package com.example.NoLimits.dto.producto.request;

import com.example.NoLimits.Multimedia.dto.producto.request.LinkCompraDTO;
import com.example.NoLimits.Multimedia.dto.producto.request.ProductoRequestDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductoRequestDTO - Cobertura de ramas equals/hashCode")
class ProductoRequestDTOBranchTest {

    private LinkCompraDTO link(Long plataformaId, String url) {
        LinkCompraDTO l = new LinkCompraDTO();
        l.setPlataformaId(plataformaId);
        l.setUrl(url);
        return l;
    }

    private ProductoRequestDTO full() {
        ProductoRequestDTO dto = new ProductoRequestDTO();
        dto.setNombre("Spider-Man");
        dto.setPrecio(12990.0);
        dto.setSinopsis("Un joven héroe descubre sus poderes.");
        dto.setUrlTrailer("https://www.youtube.com/watch?v=test");
        dto.setAnio(2002);
        dto.setTipoProductoId(2L);
        dto.setClasificacionId(3L);
        dto.setEstadoId(1L);
        dto.setSaga("Marvel");
        dto.setPortadaSaga("sagas/marvel.webp");
        dto.setPlataformasIds(List.of(1L, 2L));
        dto.setGenerosIds(List.of(3L, 4L));
        dto.setEmpresasIds(List.of(5L));
        dto.setDesarrolladoresIds(List.of(7L, 8L));
        dto.setImagenesRutas(List.of("productos/spiderman1.webp"));
        dto.setLinksCompra(List.of(link(1L, "https://store.steampowered.com/app/test")));
        dto.setTipoEmpresaId(10L);
        dto.setTipoDesarrolladorId(20L);
        return dto;
    }

    /**
     * Segunda instancia con TODOS los campos distintos a full(), del mismo tipo.
     * Esto ejercita, para cada campo, la rama "no son iguales" del equals generado.
     */
    private ProductoRequestDTO fullAlternativo() {
        ProductoRequestDTO dto = new ProductoRequestDTO();
        dto.setNombre("Mario Kart");
        dto.setPrecio(29990.0);
        dto.setSinopsis("Carreras de karts con personajes de Nintendo.");
        dto.setUrlTrailer("https://www.youtube.com/watch?v=otro");
        dto.setAnio(2017);
        dto.setTipoProductoId(99L);
        dto.setClasificacionId(98L);
        dto.setEstadoId(97L);
        dto.setSaga("Mario");
        dto.setPortadaSaga("sagas/mario.webp");
        dto.setPlataformasIds(List.of(9L));
        dto.setGenerosIds(List.of(8L));
        dto.setEmpresasIds(List.of(7L));
        dto.setDesarrolladoresIds(List.of(6L));
        dto.setImagenesRutas(List.of("productos/mariokart.webp"));
        dto.setLinksCompra(List.of(link(2L, "https://www.nintendo.com/otro")));
        dto.setTipoEmpresaId(100L);
        dto.setTipoDesarrolladorId(200L);
        return dto;
    }

    @Nested
    @DisplayName("equals - casos generales")
    class EqualsGenerales {

        @Test
        @DisplayName("es igual a sí mismo")
        void igualASiMismo() {
            ProductoRequestDTO dto = full();
            assertEquals(dto, dto);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {
            ProductoRequestDTO dto = full();
            assertNotEquals(null, dto);
        }

        @Test
        @DisplayName("no es igual a un objeto de otra clase")
        void noEsIgualAOtraClase() {
            ProductoRequestDTO dto = full();
            assertNotEquals(dto, "no soy un ProductoRequestDTO");
        }

        @Test
        @DisplayName("dos instancias completas con los mismos valores son iguales")
        void instanciasCompletasIgualesSonIguales() {
            ProductoRequestDTO dto1 = full();
            ProductoRequestDTO dto2 = full();
            assertEquals(dto1, dto2);
            assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        @DisplayName("dos instancias vacías son iguales")
        void instanciasVaciasSonIguales() {
            ProductoRequestDTO dto1 = new ProductoRequestDTO();
            ProductoRequestDTO dto2 = new ProductoRequestDTO();
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
            ProductoRequestDTO dto1 = full();
            ProductoRequestDTO dto2 = fullAlternativo();

            assertNotEquals(dto1, dto2);
            assertNotEquals(dto2, dto1);
        }

        @Test
        @DisplayName("una instancia completa no es igual a una vacía (todos los campos null vs no-null)")
        void instanciaCompletaVsVaciaNoSonIguales() {
            ProductoRequestDTO completo = full();
            ProductoRequestDTO vacio = new ProductoRequestDTO();

            assertNotEquals(completo, vacio);
            assertNotEquals(vacio, completo);
        }

        @Test
        @DisplayName("difiere solo en nombre")
        void difiereSoloEnNombre() {
            ProductoRequestDTO dto1 = full();
            ProductoRequestDTO dto2 = full();
            dto2.setNombre("Otro nombre");

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("difiere solo en nombre null vs no null")
        void difiereSoloEnNombreNull() {
            ProductoRequestDTO dto1 = full();
            ProductoRequestDTO dto2 = full();
            dto2.setNombre(null);

            assertNotEquals(dto1, dto2);
            assertNotEquals(dto2, dto1);
        }

        @Test
        @DisplayName("difiere solo en precio")
        void difiereSoloEnPrecio() {
            ProductoRequestDTO dto1 = full();
            ProductoRequestDTO dto2 = full();
            dto2.setPrecio(1.0);

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("difiere solo en anio")
        void difiereSoloEnAnio() {
            ProductoRequestDTO dto1 = full();
            ProductoRequestDTO dto2 = full();
            dto2.setAnio(1999);

            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("difiere solo en linksCompra")
        void difiereSoloEnLinksCompra() {
            ProductoRequestDTO dto1 = full();
            ProductoRequestDTO dto2 = full();
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
            ProductoRequestDTO dto = full();
            assertEquals(dto.hashCode(), dto.hashCode());
        }

        @Test
        @DisplayName("hashCode difiere cuando los valores difieren")
        void hashCodeDifiereConValoresDistintos() {
            ProductoRequestDTO dto1 = full();
            ProductoRequestDTO dto2 = fullAlternativo();
            assertNotEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        @DisplayName("hashCode de instancia vacía no lanza excepción")
        void hashCodeDeInstanciaVacia() {
            ProductoRequestDTO dto = new ProductoRequestDTO();
            assertDoesNotThrow(dto::hashCode);
        }
    }

    @Nested
    @DisplayName("toString")
    class ToStringTest {

        @Test
        @DisplayName("toString de instancia completa contiene los valores de los campos")
        void toStringContieneValores() {
            ProductoRequestDTO dto = full();
            String resultado = dto.toString();

            assertNotNull(resultado);
            assertTrue(resultado.contains("nombre"));
            assertTrue(resultado.contains("Spider-Man"));
        }

        @Test
        @DisplayName("toString de instancia vacía no lanza excepción")
        void toStringDeInstanciaVacia() {
            ProductoRequestDTO dto = new ProductoRequestDTO();
            assertDoesNotThrow(dto::toString);
        }
    }
}