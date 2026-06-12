package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.GeneroModel;
import com.example.NoLimits.Multimedia.model.catalogos.GenerosModel;
import com.example.NoLimits.Multimedia.model.producto.ProductoModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GenerosModel Tests")
class GenerosModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear relación correctamente")
        void debeCrearRelacionCorrectamente() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            GeneroModel genero = new GeneroModel();
            genero.setId(2L);

            GenerosModel model =
                    new GenerosModel(
                            100L,
                            producto,
                            genero
                    );

            assertEquals(100L, model.getId());
            assertEquals(producto, model.getProducto());
            assertEquals(genero, model.getGenero());
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

            GeneroModel genero = new GeneroModel();
            genero.setId(20L);

            GenerosModel model = new GenerosModel();

            model.setId(1L);
            model.setProducto(producto);
            model.setGenero(genero);

            assertEquals(1L, model.getId());
            assertEquals(producto, model.getProducto());
            assertEquals(genero, model.getGenero());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCode {

        @Test
        @DisplayName("mismo producto y género deben ser iguales")
        void mismoProductoYGeneroDebenSerIguales() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            GeneroModel genero = new GeneroModel();
            genero.setId(2L);

            GenerosModel r1 =
                    new GenerosModel(
                            10L,
                            producto,
                            genero
                    );

            GenerosModel r2 =
                    new GenerosModel(
                            99L,
                            producto,
                            genero
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

            GeneroModel genero = new GeneroModel();
            genero.setId(3L);

            GenerosModel r1 =
                    new GenerosModel(
                            1L,
                            producto1,
                            genero
                    );

            GenerosModel r2 =
                    new GenerosModel(
                            1L,
                            producto2,
                            genero
                    );

            assertNotEquals(r1, r2);
        }

        @Test
        @DisplayName("relaciones con producto null son iguales")
        void relacionesConProductoNullSonIguales() {

            GeneroModel genero = new GeneroModel();
            genero.setId(2L);

            GenerosModel r1 = new GenerosModel();
            r1.setGenero(genero);

            GenerosModel r2 = new GenerosModel();
            r2.setGenero(genero);

            assertEquals(r1, r2);
        }

        @Test
        @DisplayName("relaciones con genero null son iguales")
        void relacionesConGeneroNullSonIguales() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            GenerosModel r1 = new GenerosModel();
            r1.setProducto(producto);

            GenerosModel r2 = new GenerosModel();
            r2.setProducto(producto);

            assertEquals(r1, r2);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {

            GenerosModel relacion = new GenerosModel();

            assertNotEquals(relacion, null);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAOtroTipo() {

            GenerosModel relacion = new GenerosModel();

            assertNotEquals(relacion, "texto");
        }

        @Test
        @DisplayName("es igual a si mismo")
        void esIgualASiMismo() {

            GenerosModel relacion = new GenerosModel();

            assertEquals(relacion, relacion);
        }

        @Test
        @DisplayName("hashCode soporta producto y genero null")
        void hashCodeSoportaValoresNull() {

            GenerosModel relacion = new GenerosModel();

            assertDoesNotThrow(relacion::hashCode);
        }

        @Test
        @DisplayName("equals es simétrico para ids nulos")
        void equalsEsSimetricoParaIdsNulos() {

            GenerosModel r1 = new GenerosModel();
            GenerosModel r2 = new GenerosModel();

            assertTrue(r1.equals(r2));
            assertTrue(r2.equals(r1));
        }

        @Test
        @DisplayName("equals es simétrico para misma relación")
        void equalsEsSimetricoParaMismaRelacion() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            GeneroModel genero = new GeneroModel();
            genero.setId(2L);

            GenerosModel r1 = new GenerosModel(1L, producto, genero);
            GenerosModel r2 = new GenerosModel(2L, producto, genero);

            assertTrue(r1.equals(r2));
            assertTrue(r2.equals(r1));
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("no debe retornar null")
        void noDebeRetornarNull() {

            GenerosModel model = new GenerosModel();

            assertNotNull(model.toString());
        }
    }
}