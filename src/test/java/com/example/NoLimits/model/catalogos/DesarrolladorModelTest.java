package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.DesarrolladorModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DesarrolladorModel Tests")
class DesarrolladorModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear desarrollador correctamente")
        void debeCrearDesarrolladorCorrectamente() {

            DesarrolladorModel model = new DesarrolladorModel(
                    1L,
                    "Nintendo",
                    true,
                    null,
                    null
            );

            assertEquals(1L, model.getId());
            assertEquals("Nintendo", model.getNombre());
            assertTrue(model.isActivo());
            assertNull(model.getProductos());
            assertNull(model.getTipos());
        }
    }

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        @DisplayName("debe asignar y obtener propiedades")
        void debeAsignarYObtenerPropiedades() {

            DesarrolladorModel model = new DesarrolladorModel();

            model.setId(10L);
            model.setNombre("Valve");
            model.setActivo(false);

            assertEquals(10L, model.getId());
            assertEquals("Valve", model.getNombre());
            assertFalse(model.isActivo());
        }

        @Test
        @DisplayName("debe asignar lista de productos")
        void debeAsignarListaProductos() {

            DesarrolladorModel model = new DesarrolladorModel();

            model.setProductos(null);

            assertNull(model.getProductos());
        }

        @Test
        @DisplayName("debe asignar lista de tipos")
        void debeAsignarListaTipos() {

            DesarrolladorModel model = new DesarrolladorModel();

            model.setTipos(null);

            assertNull(model.getTipos());
        }
    }

    @Nested
    @DisplayName("Valores por defecto")
    class ValoresPorDefecto {

        @Test
        @DisplayName("activo debe iniciar en true")
        void activoDebeIniciarEnTrue() {

            DesarrolladorModel model = new DesarrolladorModel();

            assertTrue(model.isActivo());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCode {

        @Test
        @DisplayName("objetos con mismo id deben ser iguales")
        void objetosConMismoIdDebenSerIguales() {

            DesarrolladorModel d1 = new DesarrolladorModel();
            d1.setId(1L);

            DesarrolladorModel d2 = new DesarrolladorModel();
            d2.setId(1L);

            assertEquals(d1, d2);
            assertEquals(d1.hashCode(), d2.hashCode());
        }

        @Test
        @DisplayName("objetos con distinto id no deben ser iguales")
        void objetosConDistintoIdNoDebenSerIguales() {

            DesarrolladorModel d1 = new DesarrolladorModel();
            d1.setId(1L);

            DesarrolladorModel d2 = new DesarrolladorModel();
            d2.setId(2L);

            assertNotEquals(d1, d2);
        }

        @Test
        @DisplayName("objetos con id null son iguales")
        void objetosConIdNullSonIguales() {

            DesarrolladorModel d1 = new DesarrolladorModel();
            DesarrolladorModel d2 = new DesarrolladorModel();

            assertEquals(d1, d2);
            assertEquals(d1.hashCode(), d2.hashCode());
        }

        @Test
        @DisplayName("es igual a si mismo")
        void esIgualASiMismo() {

            DesarrolladorModel model = new DesarrolladorModel();
            model.setId(1L);

            assertEquals(model, model);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {

            DesarrolladorModel model = new DesarrolladorModel();
            model.setId(1L);

            assertNotEquals(model, null);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAOtraClase() {

            DesarrolladorModel model = new DesarrolladorModel();
            model.setId(1L);

            assertNotEquals(model, "texto");
        }

        @Test
        @DisplayName("hashCode con id null")
        void hashCodeConIdNull() {

            DesarrolladorModel model = new DesarrolladorModel();

            int hash = model.hashCode();

            assertEquals(hash, model.hashCode());
        }

        @Test
        @DisplayName("id null y id con valor no son iguales")
        void idNullYIdConValorNoSonIguales() {

            DesarrolladorModel d1 = new DesarrolladorModel();

            DesarrolladorModel d2 = new DesarrolladorModel();
            d2.setId(1L);

            assertNotEquals(d1, d2);
        }

        @Test
        @DisplayName("id con valor y id null no son iguales")
        void idConValorYIdNullNoSonIguales() {

            DesarrolladorModel d1 = new DesarrolladorModel();
            d1.setId(1L);

            DesarrolladorModel d2 = new DesarrolladorModel();

            assertNotEquals(d1, d2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("debe contener id y nombre")
        void debeContenerIdYNombre() {

            DesarrolladorModel model = new DesarrolladorModel();
            model.setId(5L);
            model.setNombre("Capcom");

            String resultado = model.toString();

            assertTrue(resultado.contains("5"));
            assertTrue(resultado.contains("Capcom"));
        }
    }
}