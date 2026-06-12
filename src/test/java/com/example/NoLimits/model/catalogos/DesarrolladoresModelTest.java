package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.DesarrolladorModel;
import com.example.NoLimits.Multimedia.model.catalogos.DesarrolladoresModel;
import com.example.NoLimits.Multimedia.model.producto.ProductoModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DesarrolladoresModel Tests")
class DesarrolladoresModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear relación correctamente")
        void debeCrearRelacionCorrectamente() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            DesarrolladorModel desarrollador =
                    new DesarrolladorModel();
            desarrollador.setId(2L);

            DesarrolladoresModel model =
                    new DesarrolladoresModel(
                            100L,
                            producto,
                            desarrollador
                    );

            assertEquals(100L, model.getId());
            assertEquals(producto, model.getProducto());
            assertEquals(desarrollador, model.getDesarrollador());
        }
    }

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        @DisplayName("debe asignar y obtener propiedades")
        void debeAsignarYObtenerPropiedades() {

            ProductoModel producto = new ProductoModel();
            producto.setId(10L);

            DesarrolladorModel desarrollador =
                    new DesarrolladorModel();
            desarrollador.setId(20L);

            DesarrolladoresModel model =
                    new DesarrolladoresModel();

            model.setId(1L);
            model.setProducto(producto);
            model.setDesarrollador(desarrollador);

            assertEquals(1L, model.getId());
            assertEquals(producto, model.getProducto());
            assertEquals(desarrollador, model.getDesarrollador());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCode {

        @Test
        @DisplayName("mismo producto y desarrollador deben ser iguales")
        void mismoProductoYDesarrolladorDebenSerIguales() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            DesarrolladorModel desarrollador =
                    new DesarrolladorModel();
            desarrollador.setId(2L);

            DesarrolladoresModel r1 =
                    new DesarrolladoresModel(
                            10L,
                            producto,
                            desarrollador
                    );

            DesarrolladoresModel r2 =
                    new DesarrolladoresModel(
                            99L,
                            producto,
                            desarrollador
                    );

            assertEquals(r1, r2);
            assertEquals(r1.hashCode(), r2.hashCode());
        }

        @Test
        @DisplayName("relaciones distintas no deben ser iguales")
        void relacionesDistintasNoDebenSerIguales() {

            ProductoModel producto1 = new ProductoModel();
            producto1.setId(1L);

            ProductoModel producto2 = new ProductoModel();
            producto2.setId(2L);

            DesarrolladorModel desarrollador =
                    new DesarrolladorModel();
            desarrollador.setId(3L);

            DesarrolladoresModel r1 =
                    new DesarrolladoresModel(
                            1L,
                            producto1,
                            desarrollador
                    );

            DesarrolladoresModel r2 =
                    new DesarrolladoresModel(
                            1L,
                            producto2,
                            desarrollador
                    );

            assertNotEquals(r1, r2);
        }

        @Test
        @DisplayName("relaciones con producto null son iguales")
        void relacionesConProductoNullSonIguales() {

                DesarrolladorModel desarrollador = new DesarrolladorModel();
                desarrollador.setId(2L);

                DesarrolladoresModel r1 = new DesarrolladoresModel();
                r1.setDesarrollador(desarrollador);

                DesarrolladoresModel r2 = new DesarrolladoresModel();
                r2.setDesarrollador(desarrollador);

                assertEquals(r1, r2);
        }

        @Test
        @DisplayName("relaciones con desarrollador null son iguales")
        void relacionesConDesarrolladorNullSonIguales() {

                ProductoModel producto = new ProductoModel();
                producto.setId(1L);

                DesarrolladoresModel r1 = new DesarrolladoresModel();
                r1.setProducto(producto);

                DesarrolladoresModel r2 = new DesarrolladoresModel();
                r2.setProducto(producto);

                assertEquals(r1, r2);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {

                DesarrolladoresModel relacion = new DesarrolladoresModel();

                assertNotEquals(relacion, null);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAOtroTipo() {

                DesarrolladoresModel relacion = new DesarrolladoresModel();

                assertNotEquals(relacion, "texto");
        }

        @Test
        @DisplayName("es igual a si mismo")
        void esIgualASiMismo() {

                DesarrolladoresModel relacion = new DesarrolladoresModel();

                assertEquals(relacion, relacion);
        }

        @Test
        @DisplayName("hashCode soporta valores null")
        void hashCodeSoportaValoresNull() {

                DesarrolladoresModel relacion = new DesarrolladoresModel();

                assertDoesNotThrow(relacion::hashCode);
        }

        @Test
        @DisplayName("desarrolladores distintos no son iguales")
        void desarrolladoresDistintosNoSonIguales() {

                ProductoModel producto = new ProductoModel();
                producto.setId(1L);

                DesarrolladorModel d1 = new DesarrolladorModel();
                d1.setId(10L);

                DesarrolladorModel d2 = new DesarrolladorModel();
                d2.setId(20L);

                DesarrolladoresModel r1 = new DesarrolladoresModel();
                r1.setProducto(producto);
                r1.setDesarrollador(d1);

                DesarrolladoresModel r2 = new DesarrolladoresModel();
                r2.setProducto(producto);
                r2.setDesarrollador(d2);

                assertNotEquals(r1, r2);
        }

        @Test
        @DisplayName("producto null versus producto con valor")
        void productoNullVsProductoConValor() {

                DesarrolladorModel desarrollador = new DesarrolladorModel();
                desarrollador.setId(1L);

                ProductoModel producto = new ProductoModel();
                producto.setId(1L);

                DesarrolladoresModel r1 = new DesarrolladoresModel();
                r1.setDesarrollador(desarrollador);

                DesarrolladoresModel r2 = new DesarrolladoresModel();
                r2.setDesarrollador(desarrollador);
                r2.setProducto(producto);

                assertNotEquals(r1, r2);
        }

        @Test
        @DisplayName("desarrollador null versus desarrollador con valor")
        void desarrolladorNullVsDesarrolladorConValor() {

                ProductoModel producto = new ProductoModel();
                producto.setId(1L);

                DesarrolladorModel desarrollador = new DesarrolladorModel();
                desarrollador.setId(1L);

                DesarrolladoresModel r1 = new DesarrolladoresModel();
                r1.setProducto(producto);

                DesarrolladoresModel r2 = new DesarrolladoresModel();
                r2.setProducto(producto);
                r2.setDesarrollador(desarrollador);

                assertNotEquals(r1, r2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("no debe retornar null")
        void noDebeRetornarNull() {

            DesarrolladoresModel model =
                    new DesarrolladoresModel();

            assertNotNull(model.toString());
        }
    }
}