package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.EmpresaModel;
import com.example.NoLimits.Multimedia.model.catalogos.TipoEmpresaModel;
import com.example.NoLimits.Multimedia.model.catalogos.TiposEmpresaModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TiposEmpresaModel Tests")
class TiposEmpresaModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear relacion correctamente")
        void debeCrearRelacionCorrectamente() {

            EmpresaModel empresa = new EmpresaModel();
            empresa.setId(1L);

            TipoEmpresaModel tipo = new TipoEmpresaModel();
            tipo.setId(2L);

            TiposEmpresaModel model =
                    new TiposEmpresaModel(
                            100L,
                            empresa,
                            tipo
                    );

            assertEquals(100L, model.getId());
            assertEquals(empresa, model.getEmpresa());
            assertEquals(tipo, model.getTipoEmpresa());
        }
    }

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        @DisplayName("debe asignar y obtener propiedades")
        void debeAsignarYObtenerPropiedades() {

            EmpresaModel empresa = new EmpresaModel();
            TipoEmpresaModel tipo = new TipoEmpresaModel();

            TiposEmpresaModel model = new TiposEmpresaModel();

            model.setId(1L);
            model.setEmpresa(empresa);
            model.setTipoEmpresa(tipo);

            assertEquals(1L, model.getId());
            assertEquals(empresa, model.getEmpresa());
            assertEquals(tipo, model.getTipoEmpresa());
        }

        @Test
        @DisplayName("permite asignar relaciones nulas")
        void permiteAsignarRelacionesNulas() {

            // Arrange
            TiposEmpresaModel model = new TiposEmpresaModel();

            // Act
            model.setEmpresa(null);
            model.setTipoEmpresa(null);

            // Assert
            assertNull(model.getEmpresa());
            assertNull(model.getTipoEmpresa());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCode {

        @Test
        @DisplayName("objetos con mismo id deben ser iguales")
        void objetosConMismoIdDebenSerIguales() {

            TiposEmpresaModel r1 = new TiposEmpresaModel();
            r1.setId(1L);

            TiposEmpresaModel r2 = new TiposEmpresaModel();
            r2.setId(1L);

            assertEquals(r1, r2);
            assertEquals(r1.hashCode(), r2.hashCode());
        }

        @Test
        @DisplayName("objetos con distinto id no deben ser iguales")
        void objetosConDistintoIdNoDebenSerIguales() {

            TiposEmpresaModel r1 = new TiposEmpresaModel();
            r1.setId(1L);

            TiposEmpresaModel r2 = new TiposEmpresaModel();
            r2.setId(2L);

            assertNotEquals(r1, r2);
        }

        @Test
        @DisplayName("objetos con id null son iguales")
        void objetosConIdNullSonIguales() {

            // Arrange
            TiposEmpresaModel t1 = new TiposEmpresaModel();
            TiposEmpresaModel t2 = new TiposEmpresaModel();

            // Act & Assert
            assertEquals(t1, t2);
        }

        @Test
        @DisplayName("id null y id con valor no son iguales")
        void idNullYIdConValorNoSonIguales() {

            TiposEmpresaModel t1 = new TiposEmpresaModel();

            TiposEmpresaModel t2 = new TiposEmpresaModel();
            t2.setId(1L);

            assertNotEquals(t1, t2);
        }

        @Test
        @DisplayName("id con valor y id null no son iguales")
        void idConValorYIdNullNoSonIguales() {

            TiposEmpresaModel t1 = new TiposEmpresaModel();
            t1.setId(1L);

            TiposEmpresaModel t2 = new TiposEmpresaModel();

            assertNotEquals(t1, t2);
        }

        @Test
        @DisplayName("es igual a si mismo")
        void esIgualASiMismo() {

            // Arrange
            TiposEmpresaModel model = new TiposEmpresaModel();

            // Act & Assert
            assertEquals(model, model);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {

            // Arrange
            TiposEmpresaModel model = new TiposEmpresaModel();

            // Act & Assert
            assertNotEquals(null, model);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAOtraClase() {

            // Arrange
            TiposEmpresaModel model = new TiposEmpresaModel();

            // Act & Assert
            assertNotEquals(model, "texto");
        }

        @Test
        @DisplayName("hashCode con id null")
        void hashCodeConIdNull() {

            // Arrange
            TiposEmpresaModel model = new TiposEmpresaModel();

            // Act
            int hash = model.hashCode();

            // Assert
            assertEquals(hash, model.hashCode());
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("toString no debe retornar null")
        void toStringNoDebeRetornarNull() {

            TiposEmpresaModel model = new TiposEmpresaModel();

            assertNotNull(model.toString());
        }

        @Test
        @DisplayName("toString contiene id")
        void toStringContieneId() {

            // Arrange
            TiposEmpresaModel model = new TiposEmpresaModel();
            model.setId(5L);

            // Act
            String resultado = model.toString();

            // Assert
            assertTrue(resultado.contains("5"));
        }
    }
}