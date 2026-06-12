package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.TipoEmpresaModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TipoEmpresaModel Tests")
class TipoEmpresaModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear tipo de empresa correctamente")
        void debeCrearTipoDeEmpresaCorrectamente() {

            TipoEmpresaModel model =
                    new TipoEmpresaModel(
                            1L,
                            "Publisher",
                            null
                    );

            assertEquals(1L, model.getId());
            assertEquals("Publisher", model.getNombre());
            assertNull(model.getEmpresas());
        }
    }

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        @DisplayName("debe asignar y obtener propiedades")
        void debeAsignarYObtenerPropiedades() {

            TipoEmpresaModel model =
                    new TipoEmpresaModel();

            model.setId(10L);
            model.setNombre("Distribuidora");

            assertEquals(10L, model.getId());
            assertEquals("Distribuidora", model.getNombre());
        }

        @Test
        @DisplayName("debe asignar lista de empresas")
        void debeAsignarListaEmpresas() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            model.setEmpresas(null);

            assertNull(model.getEmpresas());
        }

        @Test
        @DisplayName("debe asignar y obtener empresas")
        void debeAsignarYObtenerEmpresas() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            model.setEmpresas(java.util.List.of());

            assertNotNull(model.getEmpresas());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCode {

        @Test
        @DisplayName("objetos con mismo contenido son iguales")
        void objetosConMismoContenidoSonIguales() {

            TipoEmpresaModel t1 =
                    new TipoEmpresaModel();
            t1.setId(1L);
            t1.setNombre("Publisher");

            TipoEmpresaModel t2 =
                    new TipoEmpresaModel();
            t2.setId(1L);
            t2.setNombre("Publisher");

            assertEquals(t1, t2);
            assertEquals(t1.hashCode(), t2.hashCode());
        }

        @Test
        @DisplayName("objetos con distinto id no son iguales")
        void objetosConDistintoIdNoSonIguales() {

            TipoEmpresaModel t1 =
                    new TipoEmpresaModel();
            t1.setId(1L);

            TipoEmpresaModel t2 =
                    new TipoEmpresaModel();
            t2.setId(2L);

            assertNotEquals(t1, t2);
        }

        @Test
        @DisplayName("es igual a si mismo")
        void esIgualASiMismo() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            assertEquals(model, model);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            assertNotEquals(model, null);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAOtraClase() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            assertNotEquals(model, "texto");
        }

        @Test
        @DisplayName("objetos con nombre distinto no son iguales")
        void objetosConNombreDistintoNoSonIguales() {

            TipoEmpresaModel t1 = new TipoEmpresaModel();
            t1.setId(1L);
            t1.setNombre("Publisher");

            TipoEmpresaModel t2 = new TipoEmpresaModel();
            t2.setId(1L);
            t2.setNombre("Distribuidora");

            assertNotEquals(t1, t2);
        }

        @Test
        @DisplayName("objetos con nombre null son iguales")
        void objetosConNombreNullSonIguales() {

            TipoEmpresaModel t1 = new TipoEmpresaModel();
            t1.setId(1L);

            TipoEmpresaModel t2 = new TipoEmpresaModel();
            t2.setId(1L);

            assertEquals(t1, t2);
        }

        @Test
        @DisplayName("hashCode soporta valores null")
        void hashCodeSoportaValoresNull() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            assertDoesNotThrow(model::hashCode);
        }

        @Test
        @DisplayName("empresas null en ambos objetos")
        void empresasNullEnAmbosObjetos() {

            TipoEmpresaModel t1 =
                    new TipoEmpresaModel(
                            1L,
                            "Publisher",
                            null
                    );

            TipoEmpresaModel t2 =
                    new TipoEmpresaModel(
                            1L,
                            "Publisher",
                            null
                    );

            assertEquals(t1, t2);
        }

        @Test
        @DisplayName("empresas distintas no son iguales")
        void empresasDistintasNoSonIguales() {

            TipoEmpresaModel t1 =
                    new TipoEmpresaModel(
                            1L,
                            "Publisher",
                            java.util.List.of()
                    );

            TipoEmpresaModel t2 =
                    new TipoEmpresaModel(
                            1L,
                            "Publisher",
                            null
                    );

            assertNotEquals(t1, t2);
        }

        @Test
        @DisplayName("objetos con listas distintas no son iguales")
        void objetosConListasDistintasNoSonIguales() {

            TipoEmpresaModel t1 =
                    new TipoEmpresaModel(
                            1L,
                            "Publisher",
                            java.util.List.of()
                    );

            TipoEmpresaModel t2 =
                    new TipoEmpresaModel(
                            1L,
                            "Publisher",
                            null
                    );

            assertNotEquals(t1, t2);
        }

        @Test
        @DisplayName("equals retorna false con null")
        void equalsConNull() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            assertNotEquals(model, null);
        }

        @Test
        @DisplayName("equals retorna false con otra clase")
        void equalsConOtraClase() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            assertNotEquals(model, "texto");
        }

        @Test
        @DisplayName("equals retorna true consigo mismo")
        void equalsConsigoMismo() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            assertEquals(model, model);
        }

        @Test
        @DisplayName("hashCode soporta campos null")
        void hashCodeSoportaCamposNull() {

            TipoEmpresaModel model = new TipoEmpresaModel();

            assertDoesNotThrow(model::hashCode);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("toString no debe ser null")
        void toStringNoDebeSerNull() {

            TipoEmpresaModel model =
                    new TipoEmpresaModel();

            assertNotNull(model.toString());
        }
    }
}