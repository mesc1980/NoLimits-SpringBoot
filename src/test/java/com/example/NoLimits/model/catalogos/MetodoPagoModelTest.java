package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.MetodoPagoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MetodoPagoModel Tests")
class MetodoPagoModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear metodo de pago correctamente")
        void debeCrearMetodoPagoCorrectamente() {

            MetodoPagoModel model = new MetodoPagoModel(
                    1L,
                    "Tarjeta de Crédito",
                    true,
                    null
            );

            assertEquals(1L, model.getId());
            assertEquals("Tarjeta de Crédito", model.getNombre());
            assertTrue(model.getActivo());
            assertNull(model.getVentas());
        }
    }

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        @DisplayName("debe asignar y obtener propiedades")
        void debeAsignarYObtenerPropiedades() {

            MetodoPagoModel model = new MetodoPagoModel();

            model.setId(10L);
            model.setNombre("Transferencia");
            model.setActivo(false);

            assertEquals(10L, model.getId());
            assertEquals("Transferencia", model.getNombre());
            assertFalse(model.getActivo());
        }

        @Test
        @DisplayName("setVentas y getVentas funcionan")
        void setVentasYGetVentasFuncionan() {

            MetodoPagoModel model = new MetodoPagoModel();

            model.setVentas(new java.util.ArrayList<>());

            assertNotNull(model.getVentas());
        }
    }

    @Nested
    @DisplayName("Valor por defecto")
    class ValorPorDefecto {

        @Test
        @DisplayName("activo inicia en true")
        void activoIniciaEnTrue() {

            MetodoPagoModel model = new MetodoPagoModel();

            assertTrue(model.getActivo());
        }
    }

    @Nested
    @DisplayName("Metodo validarMetodo")
    class MetodoValidarMetodo {

        @Test
        @DisplayName("retorna true cuando el nombre es válido")
        void retornaTrueCuandoNombreEsValido() {

            MetodoPagoModel model = new MetodoPagoModel();
            model.setNombre("Tarjeta");

            assertTrue(model.validarMetodo());
        }

        @Test
        @DisplayName("retorna false cuando el nombre es null")
        void retornaFalseCuandoNombreEsNull() {

            MetodoPagoModel model = new MetodoPagoModel();
            model.setNombre(null);

            assertFalse(model.validarMetodo());
        }

        @Test
        @DisplayName("retorna false cuando el nombre está vacío")
        void retornaFalseCuandoNombreEstaVacio() {

            MetodoPagoModel model = new MetodoPagoModel();
            model.setNombre("   ");

            assertFalse(model.validarMetodo());
        }
    }

    @Nested
    @DisplayName("Metodo esActivo")
    class MetodoEsActivo {

        @Test
        void retornaTrueCuandoActivoEsTrue() {

            MetodoPagoModel model = new MetodoPagoModel();
            model.setActivo(true);

            assertTrue(model.esActivo());
        }

        @Test
        void retornaFalseCuandoActivoEsFalse() {

            MetodoPagoModel model = new MetodoPagoModel();
            model.setActivo(false);

            assertFalse(model.esActivo());
        }

        @Test
        void retornaFalseCuandoActivoEsNull() {

            MetodoPagoModel model = new MetodoPagoModel();
            model.setActivo(null);

            assertFalse(model.esActivo());
        }
    }

    @Nested
    @DisplayName("Equals HashCode y ToString")
    class EqualsHashCodeToString {

        @Test
        @DisplayName("objetos con mismo contenido son iguales")
        void objetosConMismoContenidoSonIguales() {

            MetodoPagoModel m1 = new MetodoPagoModel();
            m1.setId(1L);
            m1.setNombre("Tarjeta");

            MetodoPagoModel m2 = new MetodoPagoModel();
            m2.setId(1L);
            m2.setNombre("Tarjeta");

            assertEquals(m1, m2);
            assertEquals(m1.hashCode(), m2.hashCode());
        }

        @Test
        @DisplayName("toString no debe ser null")
        void toStringNoDebeSerNull() {

            MetodoPagoModel model = new MetodoPagoModel();

            assertNotNull(model.toString());
        }

        @Test
        @DisplayName("equals retorna false con null")
        void equalsConNull() {

            MetodoPagoModel model = new MetodoPagoModel();

            assertNotEquals(null, model);
        }

        @Test
        @DisplayName("equals retorna false con otra clase")
        void equalsConOtraClase() {

            MetodoPagoModel model = new MetodoPagoModel();

            assertNotEquals("texto", model);
        }

        @Test
        @DisplayName("equals retorna true consigo mismo")
        void equalsConsigoMismo() {

            MetodoPagoModel model = new MetodoPagoModel();

            assertEquals(model, model);
        }

        @Test
        @DisplayName("equals retorna false cuando id es distinto")
        void equalsIdDistinto() {

            MetodoPagoModel a =
                    new MetodoPagoModel(
                            1L,
                            "Tarjeta",
                            true,
                            null
                    );

            MetodoPagoModel b =
                    new MetodoPagoModel(
                            2L,
                            "Tarjeta",
                            true,
                            null
                    );

            assertNotEquals(a, b);
        }

        @Test
        @DisplayName("equals retorna false cuando nombre es distinto")
        void equalsNombreDistinto() {

            MetodoPagoModel a =
                    new MetodoPagoModel(
                            1L,
                            "Tarjeta",
                            true,
                            null
                    );

            MetodoPagoModel b =
                    new MetodoPagoModel(
                            1L,
                            "Transferencia",
                            true,
                            null
                    );

            assertNotEquals(a, b);
        }

        @Test
        @DisplayName("equals retorna false cuando activo es distinto")
        void equalsActivoDistinto() {

            MetodoPagoModel a =
                    new MetodoPagoModel(
                            1L,
                            "Tarjeta",
                            true,
                            null
                    );

            MetodoPagoModel b =
                    new MetodoPagoModel(
                            1L,
                            "Tarjeta",
                            false,
                            null
                    );

            assertNotEquals(a, b);
        }

        @Test
        @DisplayName("equals retorna false cuando ventas son distintas")
        void equalsVentasDistintas() {

            MetodoPagoModel a =
                    new MetodoPagoModel(
                            1L,
                            "Tarjeta",
                            true,
                            java.util.List.of()
                    );

            MetodoPagoModel b =
                    new MetodoPagoModel(
                            1L,
                            "Tarjeta",
                            true,
                            null
                    );

            assertNotEquals(a, b);
        }

        @Test
        @DisplayName("equals cubre campos null")
        void equalsCamposNull() {

            MetodoPagoModel a =
                    new MetodoPagoModel(
                            1L,
                            null,
                            null,
                            null
                    );

            MetodoPagoModel b =
                    new MetodoPagoModel(
                            1L,
                            null,
                            null,
                            null
                    );

            assertEquals(a, b);
        }

        @Test
        @DisplayName("hashCode soporta campos null")
        void hashCodeCamposNull() {

            MetodoPagoModel model =
                    new MetodoPagoModel(
                            null,
                            null,
                            null,
                            null
                    );

            assertDoesNotThrow(model::hashCode);
        }
    }
}