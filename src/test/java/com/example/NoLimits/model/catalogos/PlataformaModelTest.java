package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.PlataformaModel;
import com.example.NoLimits.Multimedia.model.catalogos.PlataformasModel;
import com.example.NoLimits.Multimedia.model.producto.ProductoModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PlataformaModel Tests")
class PlataformaModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear plataforma correctamente")
        void debeCrearPlataformaCorrectamente() {

            PlataformaModel model = new PlataformaModel(
                    1L,
                    "PC",
                    null,
                    null
            );

            assertEquals(1L, model.getId());
            assertEquals("PC", model.getNombre());
            assertNull(model.getProductos());
            assertNull(model.getLinksCompra());
        }
    }

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        @DisplayName("debe asignar y obtener propiedades")
        void debeAsignarYObtenerPropiedades() {

            PlataformaModel model = new PlataformaModel();

            model.setId(10L);
            model.setNombre("PlayStation 5");

            assertEquals(10L, model.getId());
            assertEquals("PlayStation 5", model.getNombre());
        }

        @Test
        @DisplayName("debe asignar lista de productos")
        void debeAsignarListaProductos() {

            PlataformaModel model = new PlataformaModel();

            model.setProductos(null);

            assertNull(model.getProductos());
        }

        @Test
        @DisplayName("debe asignar lista de links de compra")
        void debeAsignarListaLinksCompra() {

            PlataformaModel model = new PlataformaModel();

            model.setLinksCompra(null);

            assertNull(model.getLinksCompra());
        }
    }

    @Nested
    @DisplayName("Valores por defecto")
    class ValoresPorDefecto {

        @Test
        @DisplayName("listas deben inicializarse vacías")
        void listasDebenInicializarseVacias() {

            PlataformaModel model = new PlataformaModel();

            assertNotNull(model.getProductos());
            assertNotNull(model.getLinksCompra());

            assertTrue(model.getProductos().isEmpty());
            assertTrue(model.getLinksCompra().isEmpty());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCode {

        @Test
        @DisplayName("objetos con mismo id deben ser iguales")
        void objetosConMismoIdDebenSerIguales() {

            PlataformaModel p1 = new PlataformaModel();
            p1.setId(1L);

            PlataformaModel p2 = new PlataformaModel();
            p2.setId(1L);

            assertEquals(p1, p2);
            assertEquals(p1.hashCode(), p2.hashCode());
        }

        @Test
        @DisplayName("objetos con distinto id no deben ser iguales")
        void objetosConDistintoIdNoDebenSerIguales() {

            PlataformaModel p1 = new PlataformaModel();
            p1.setId(1L);

            PlataformaModel p2 = new PlataformaModel();
            p2.setId(2L);

            assertNotEquals(p1, p2);
        }

        @Test
        @DisplayName("objetos con id null son iguales")
        void objetosConIdNullSonIguales() {

            PlataformaModel p1 = new PlataformaModel();
            PlataformaModel p2 = new PlataformaModel();

            assertEquals(p1, p2);
            assertEquals(p1.hashCode(), p2.hashCode());
        }

        @Test
        @DisplayName("es igual a si mismo")
        void esIgualASiMismo() {

            PlataformaModel plataforma = new PlataformaModel();
            plataforma.setId(1L);

            assertEquals(plataforma, plataforma);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {

            PlataformaModel plataforma = new PlataformaModel();
            plataforma.setId(1L);

            assertNotEquals(plataforma, null);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAObjetoDeOtraClase() {

            PlataformaModel plataforma = new PlataformaModel();
            plataforma.setId(1L);

            assertNotEquals(plataforma, "texto");
        }

        @Test
        @DisplayName("relaciones con producto null son iguales")
        void relacionesConProductoNullSonIguales() {

            PlataformaModel plataforma = new PlataformaModel();
            plataforma.setId(2L);

            PlataformasModel r1 = new PlataformasModel();
            r1.setPlataforma(plataforma);

            PlataformasModel r2 = new PlataformasModel();
            r2.setPlataforma(plataforma);

            assertEquals(r1, r2);
        }

        @Test
        @DisplayName("relaciones con plataforma null son iguales")
        void relacionesConPlataformaNullSonIguales() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            PlataformasModel r1 = new PlataformasModel();
            r1.setProducto(producto);

            PlataformasModel r2 = new PlataformasModel();
            r2.setProducto(producto);

            assertEquals(r1, r2);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAOtroTipo() {

            PlataformasModel relacion = new PlataformasModel();

            assertNotEquals(relacion, "texto");
        }


        @Test
        @DisplayName("hashCode con id null")
        void hashCodeConIdNull() {

            PlataformaModel plataforma = new PlataformaModel();

            int hash = plataforma.hashCode();

            assertDoesNotThrow(plataforma::hashCode);
            assertEquals(hash, plataforma.hashCode());
        }

        @Test
        @DisplayName("hashCode soporta valores null")
        void hashCodeSoportaValoresNull() {

            PlataformasModel relacion = new PlataformasModel();

            assertDoesNotThrow(relacion::hashCode);
        }

        @Test
        @DisplayName("equals con ids nulos es simétrico")
        void equalsConIdsNulosEsSimetrico() {

            PlataformaModel p1 = new PlataformaModel();
            PlataformaModel p2 = new PlataformaModel();

            assertTrue(p1.equals(p2));
            assertTrue(p2.equals(p1));
        }

        @Test
        @DisplayName("equals con mismo id es simétrico")
        void equalsConMismoIdEsSimetrico() {

            PlataformaModel p1 = new PlataformaModel();
            p1.setId(1L);

            PlataformaModel p2 = new PlataformaModel();
            p2.setId(1L);

            assertTrue(p1.equals(p2));
            assertTrue(p2.equals(p1));
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("debe contener id y nombre")
        void debeContenerIdYNombre() {

            PlataformaModel model = new PlataformaModel();
            model.setId(5L);
            model.setNombre("Xbox Series X");

            String resultado = model.toString();

            assertTrue(resultado.contains("5"));
            assertTrue(resultado.contains("Xbox Series X"));
        }
    }
}