package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.EmpresaModel;
import com.example.NoLimits.Multimedia.model.catalogos.EmpresasModel;
import com.example.NoLimits.Multimedia.model.producto.ProductoModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EmpresasModel Tests")
class EmpresasModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear relación correctamente")
        void debeCrearRelacionCorrectamente() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            EmpresaModel empresa = new EmpresaModel();
            empresa.setId(2L);

            EmpresasModel model =
                    new EmpresasModel(
                            100L,
                            producto,
                            empresa
                    );

            assertEquals(100L, model.getId());
            assertEquals(producto, model.getProducto());
            assertEquals(empresa, model.getEmpresa());
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

            EmpresaModel empresa = new EmpresaModel();
            empresa.setId(20L);

            EmpresasModel model = new EmpresasModel();

            model.setId(1L);
            model.setProducto(producto);
            model.setEmpresa(empresa);

            assertEquals(1L, model.getId());
            assertEquals(producto, model.getProducto());
            assertEquals(empresa, model.getEmpresa());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCode {

        @Test
        @DisplayName("mismo producto y empresa deben ser iguales")
        void mismoProductoYEmpresaDebenSerIguales() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            EmpresaModel empresa = new EmpresaModel();
            empresa.setId(2L);

            EmpresasModel r1 =
                    new EmpresasModel(
                            10L,
                            producto,
                            empresa
                    );

            EmpresasModel r2 =
                    new EmpresasModel(
                            99L,
                            producto,
                            empresa
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

            EmpresaModel empresa = new EmpresaModel();
            empresa.setId(3L);

            EmpresasModel r1 =
                    new EmpresasModel(
                            1L,
                            producto1,
                            empresa
                    );

            EmpresasModel r2 =
                    new EmpresasModel(
                            1L,
                            producto2,
                            empresa
                    );

            assertNotEquals(r1, r2);
        }

        @Test
        @DisplayName("relaciones con producto null son iguales")
        void relacionesConProductoNullSonIguales() {

            EmpresaModel empresa = new EmpresaModel();
            empresa.setId(2L);

            EmpresasModel r1 = new EmpresasModel();
            r1.setEmpresa(empresa);

            EmpresasModel r2 = new EmpresasModel();
            r2.setEmpresa(empresa);

            assertEquals(r1, r2);
        }

        @Test
        @DisplayName("relaciones con empresa null son iguales")
        void relacionesConEmpresaNullSonIguales() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            EmpresasModel r1 = new EmpresasModel();
            r1.setProducto(producto);

            EmpresasModel r2 = new EmpresasModel();
            r2.setProducto(producto);

            assertEquals(r1, r2);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {

            EmpresasModel relacion = new EmpresasModel();

            assertNotEquals(relacion, null);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAOtroTipo() {

            EmpresasModel relacion = new EmpresasModel();

            assertNotEquals(relacion, "texto");
        }

        @Test
        @DisplayName("es igual a si mismo")
        void esIgualASiMismo() {

            EmpresasModel relacion = new EmpresasModel();

            assertEquals(relacion, relacion);
        }

        @Test
        @DisplayName("hashCode soporta valores null")
        void hashCodeSoportaValoresNull() {

            EmpresasModel relacion = new EmpresasModel();

            assertDoesNotThrow(relacion::hashCode);
        }

        @Test
        @DisplayName("relaciones con empresa distinta no son iguales")
        void relacionesConEmpresaDistintaNoSonIguales() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            EmpresaModel empresa1 = new EmpresaModel();
            empresa1.setId(10L);

            EmpresaModel empresa2 = new EmpresaModel();
            empresa2.setId(20L);

            EmpresasModel r1 = new EmpresasModel();
            r1.setProducto(producto);
            r1.setEmpresa(empresa1);

            EmpresasModel r2 = new EmpresasModel();
            r2.setProducto(producto);
            r2.setEmpresa(empresa2);

            assertNotEquals(r1, r2);
        }

        @Test
        @DisplayName("producto null versus producto con valor no son iguales")
        void productoNullVsProductoConValor() {

            EmpresaModel empresa = new EmpresaModel();
            empresa.setId(1L);

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            EmpresasModel r1 = new EmpresasModel();
            r1.setEmpresa(empresa);

            EmpresasModel r2 = new EmpresasModel();
            r2.setEmpresa(empresa);
            r2.setProducto(producto);

            assertNotEquals(r1, r2);
        }

        @Test
        @DisplayName("empresa null versus empresa con valor no son iguales")
        void empresaNullVsEmpresaConValor() {

            ProductoModel producto = new ProductoModel();
            producto.setId(1L);

            EmpresaModel empresa = new EmpresaModel();
            empresa.setId(1L);

            EmpresasModel r1 = new EmpresasModel();
            r1.setProducto(producto);

            EmpresasModel r2 = new EmpresasModel();
            r2.setProducto(producto);
            r2.setEmpresa(empresa);

            assertNotEquals(r1, r2);
        }

        @Test
        @DisplayName("ambos valores null son iguales")
        void ambosValoresNullSonIguales() {

            EmpresasModel r1 = new EmpresasModel();
            EmpresasModel r2 = new EmpresasModel();

            assertEquals(r1, r2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("no debe retornar null")
        void noDebeRetornarNull() {

            EmpresasModel model = new EmpresasModel();

            assertNotNull(model.toString());
        }
    }
}