package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.EmpresaModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EmpresaModel Tests")
class EmpresaModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear empresa correctamente")
        void debeCrearEmpresaCorrectamente() {

            EmpresaModel model = new EmpresaModel(
                    1L,
                    "Nintendo",
                    true,
                    null,
                    null
            );

            assertEquals(1L, model.getId());
            assertEquals("Nintendo", model.getNombre());
            assertTrue(model.getActivo());
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

            EmpresaModel model = new EmpresaModel();

            model.setId(10L);
            model.setNombre("Valve");
            model.setActivo(false);

            assertEquals(10L, model.getId());
            assertEquals("Valve", model.getNombre());
            assertFalse(model.getActivo());
        }

        @Test
        @DisplayName("debe asignar lista de productos")
        void debeAsignarListaProductos() {

            EmpresaModel model = new EmpresaModel();

            model.setProductos(null);

            assertNull(model.getProductos());
        }

        @Test
        @DisplayName("debe asignar lista de tipos")
        void debeAsignarListaTipos() {

            EmpresaModel model = new EmpresaModel();

            model.setTipos(null);

            assertNull(model.getTipos());
        }

        @Test
        @DisplayName("permite asignar activo en null")
        void permiteAsignarActivoNull() {

            EmpresaModel model = new EmpresaModel();

            model.setActivo(null);

            assertNull(model.getActivo());
        }
    }

    @Nested
    @DisplayName("Valores por defecto")
    class ValoresPorDefecto {

        @Test
        @DisplayName("activo debe iniciar en true")
        void activoDebeIniciarEnTrue() {

            EmpresaModel model = new EmpresaModel();

            assertTrue(model.getActivo());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCode {

        @Test
        @DisplayName("objetos con mismo id deben ser iguales")
        void objetosConMismoIdDebenSerIguales() {

            EmpresaModel e1 = new EmpresaModel();
            e1.setId(1L);

            EmpresaModel e2 = new EmpresaModel();
            e2.setId(1L);

            assertEquals(e1, e2);
            assertEquals(e1.hashCode(), e2.hashCode());
        }

        @Test
        @DisplayName("objetos con distinto id no deben ser iguales")
        void objetosConDistintoIdNoDebenSerIguales() {

            EmpresaModel e1 = new EmpresaModel();
            e1.setId(1L);

            EmpresaModel e2 = new EmpresaModel();
            e2.setId(2L);

            assertNotEquals(e1, e2);
        }

        @Test
        @DisplayName("objetos con id null son iguales")
        void objetosConIdNullSonIguales() {

            EmpresaModel e1 = new EmpresaModel();
            EmpresaModel e2 = new EmpresaModel();

            assertEquals(e1, e2);
            assertEquals(e1.hashCode(), e2.hashCode());
        }

        @Test
        @DisplayName("es igual a si mismo")
        void esIgualASiMismo() {

            EmpresaModel model = new EmpresaModel();
            model.setId(1L);

            assertEquals(model, model);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {

            EmpresaModel model = new EmpresaModel();
            model.setId(1L);

            assertNotEquals(model, null);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAOtraClase() {

            EmpresaModel model = new EmpresaModel();
            model.setId(1L);

            assertNotEquals(model, "texto");
        }

        @Test
        @DisplayName("hashCode con id null")
        void hashCodeConIdNull() {

            EmpresaModel model = new EmpresaModel();

            int hash = model.hashCode();

            assertEquals(hash, model.hashCode());
        }

        @Test
        @DisplayName("id null y id con valor no son iguales")
        void idNullYIdConValorNoSonIguales() {

            EmpresaModel e1 = new EmpresaModel();

            EmpresaModel e2 = new EmpresaModel();
            e2.setId(1L);

            assertNotEquals(e1, e2);
        }

        @Test
        @DisplayName("id con valor y id null no son iguales")
        void idConValorYIdNullNoSonIguales() {

            EmpresaModel e1 = new EmpresaModel();
            e1.setId(1L);

            EmpresaModel e2 = new EmpresaModel();

            assertNotEquals(e1, e2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("debe contener id y nombre")
        void debeContenerIdYNombre() {

            EmpresaModel model = new EmpresaModel();
            model.setId(5L);
            model.setNombre("Capcom");

            String resultado = model.toString();

            assertTrue(resultado.contains("5"));
            assertTrue(resultado.contains("Capcom"));
        }
    }
}