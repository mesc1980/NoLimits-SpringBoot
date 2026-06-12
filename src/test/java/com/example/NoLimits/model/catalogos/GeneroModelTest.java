package com.example.NoLimits.model.catalogos;

import com.example.NoLimits.Multimedia.model.catalogos.GeneroModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GeneroModel Tests")
class GeneroModelTest {

    @Nested
    @DisplayName("Constructor completo")
    class ConstructorCompleto {

        @Test
        @DisplayName("debe crear genero correctamente")
        void debeCrearGeneroCorrectamente() {

            GeneroModel model = new GeneroModel(
                    1L,
                    "Acción",
                    null
            );

            assertEquals(1L, model.getId());
            assertEquals("Acción", model.getNombre());
            assertNull(model.getProductos());
        }
    }

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        @DisplayName("debe asignar y obtener propiedades")
        void debeAsignarYObtenerPropiedades() {

            GeneroModel model = new GeneroModel();

            model.setId(10L);
            model.setNombre("RPG");

            assertEquals(10L, model.getId());
            assertEquals("RPG", model.getNombre());
        }

        @Test
        @DisplayName("debe asignar lista de productos")
        void debeAsignarListaProductos() {

            // Arrange
            GeneroModel model = new GeneroModel();

            // Act
            model.setProductos(null);

            // Assert
            assertNull(model.getProductos());
        }
    }

    @Nested
    @DisplayName("Equals y HashCode")
    class EqualsHashCode {

        @Test
        @DisplayName("objetos con mismo id deben ser iguales")
        void objetosConMismoIdDebenSerIguales() {

            GeneroModel g1 = new GeneroModel();
            g1.setId(1L);

            GeneroModel g2 = new GeneroModel();
            g2.setId(1L);

            assertEquals(g1, g2);
            assertEquals(g1.hashCode(), g2.hashCode());
        }

        @Test
        @DisplayName("objetos con distinto id no deben ser iguales")
        void objetosConDistintoIdNoDebenSerIguales() {

            GeneroModel g1 = new GeneroModel();
            g1.setId(1L);

            GeneroModel g2 = new GeneroModel();
            g2.setId(2L);

            assertNotEquals(g1, g2);
        }

        @Test
        @DisplayName("objetos con id null son iguales")
        void objetosConIdNullSonIguales() {

            // Arrange
            GeneroModel g1 = new GeneroModel();
            GeneroModel g2 = new GeneroModel();

            // Act & Assert
            assertEquals(g1, g2);
            assertEquals(g1.hashCode(), g2.hashCode());
        }

        @Test
        @DisplayName("id null y id con valor no son iguales")
        void idNullYIdConValorNoSonIguales() {

            // Arrange
            GeneroModel g1 = new GeneroModel();

            GeneroModel g2 = new GeneroModel();
            g2.setId(1L);

            // Act & Assert
            assertNotEquals(g1, g2);
        }
        
        @Test
        @DisplayName("id con valor y id null no son iguales")
        void idConValorYIdNullNoSonIguales() {

            // Arrange
            GeneroModel g1 = new GeneroModel();
            g1.setId(1L);

            GeneroModel g2 = new GeneroModel();

            // Act & Assert
            assertNotEquals(g1, g2);
        }

        @Test
        @DisplayName("es igual a si mismo")
        void esIgualASiMismo() {

            // Arrange
            GeneroModel genero = new GeneroModel();
            genero.setId(1L);

            // Act & Assert
            assertEquals(genero, genero);
        }

        @Test
        @DisplayName("no es igual a null")
        void noEsIgualANull() {

            // Arrange
            GeneroModel genero = new GeneroModel();
            genero.setId(1L);

            // Act & Assert
            assertNotEquals(genero, null);
        }

        @Test
        @DisplayName("no es igual a objeto de otra clase")
        void noEsIgualAObjetoDeOtraClase() {

            // Arrange
            GeneroModel genero = new GeneroModel();
            genero.setId(1L);

            // Act & Assert
            assertNotEquals(genero, "texto");
        }

        @Test
        @DisplayName("equals es simétrico para ids nulos")
        void equalsEsSimetricoParaIdsNulos() {

            // Arrange
            GeneroModel g1 = new GeneroModel();
            GeneroModel g2 = new GeneroModel();

            // Act & Assert
            assertTrue(g1.equals(g2));
            assertTrue(g2.equals(g1));
        }

        @Test
        @DisplayName("equals es simétrico para mismo id")
        void equalsEsSimetricoParaMismoId() {

            // Arrange
            GeneroModel g1 = new GeneroModel();
            g1.setId(1L);

            GeneroModel g2 = new GeneroModel();
            g2.setId(1L);

            // Act & Assert
            assertTrue(g1.equals(g2));
            assertTrue(g2.equals(g1));
        }
    
        @Test
        @DisplayName("hashCode con id null")
        void hashCodeConIdNull() {

            // Arrange
            GeneroModel genero = new GeneroModel();

            // Act
            int hash = genero.hashCode();

            // Assert
            assertDoesNotThrow(() -> genero.hashCode());
            assertEquals(hash, genero.hashCode());
        } 
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("debe contener id y nombre")
        void debeContenerIdYNombre() {

            GeneroModel model = new GeneroModel();
            model.setId(5L);
            model.setNombre("Aventura");

            String resultado = model.toString();

            assertTrue(resultado.contains("5"));
            assertTrue(resultado.contains("Aventura"));
        }
    }
}