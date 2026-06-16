package com.example.NoLimits.dto.producto.request;

import com.example.NoLimits.Multimedia.dto.producto.request.DetalleVentaRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetalleVentaRequestDTOTest {

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        @DisplayName("asigna y obtiene propiedades correctamente")
        void testGettersSetters() {
            DetalleVentaRequestDTO dto = new DetalleVentaRequestDTO();
            dto.setProductoId(10L);
            dto.setCantidad(2);
            dto.setPrecioUnitario(12990F);

            assertEquals(10L, dto.getProductoId());
            assertEquals(2, dto.getCantidad());
            assertEquals(12990F, dto.getPrecioUnitario());
        }
    }

    @Nested
    @DisplayName("Valores por defecto")
    class ValoresPorDefecto {

        @Test
        @DisplayName("inicia con campos nulos")
        void testValoresPorDefecto() {
            DetalleVentaRequestDTO dto = new DetalleVentaRequestDTO();
            assertNull(dto.getProductoId());
            assertNull(dto.getCantidad());
            assertNull(dto.getPrecioUnitario());
        }
    }

    @Nested
    @DisplayName("Métodos Lombok")
    class MetodosLombok {

        @Test
        @DisplayName("genera equals y hashCode correctamente")
        void testEqualsYHashCode() {
            DetalleVentaRequestDTO dto1 = new DetalleVentaRequestDTO();
            dto1.setProductoId(10L);
            dto1.setCantidad(2);
            dto1.setPrecioUnitario(12990F);

            DetalleVentaRequestDTO dto2 = new DetalleVentaRequestDTO();
            dto2.setProductoId(10L);
            dto2.setCantidad(2);
            dto2.setPrecioUnitario(12990F);

            assertEquals(dto1, dto2);
            assertEquals(dto1.hashCode(), dto2.hashCode());
        }

        @Test
        @DisplayName("equals retorna true para misma instancia")
        void equalsMismaInstancia() {
            DetalleVentaRequestDTO dto = new DetalleVentaRequestDTO();
            assertEquals(dto, dto);
        }

        @Test
        @DisplayName("equals retorna false con null")
        void equalsConNull() {
            assertNotEquals(null, new DetalleVentaRequestDTO());
        }

        @Test
        @DisplayName("equals retorna false con otra clase")
        void equalsConOtraClase() {
            assertNotEquals("texto", new DetalleVentaRequestDTO());
        }

        @Test
        @DisplayName("objetos vacíos son iguales entre sí")
        void objetosVaciosSonIguales() {
            assertEquals(new DetalleVentaRequestDTO(), new DetalleVentaRequestDTO());
            assertEquals(new DetalleVentaRequestDTO().hashCode(), new DetalleVentaRequestDTO().hashCode());
        }

        @Test
        @DisplayName("equals detecta diferencia en productoId")
        void equalsProductoIdDistinto() {
            DetalleVentaRequestDTO dto1 = new DetalleVentaRequestDTO(); dto1.setProductoId(1L);
            DetalleVentaRequestDTO dto2 = new DetalleVentaRequestDTO(); dto2.setProductoId(2L);
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("equals detecta diferencia en cantidad")
        void equalsCantidadDistinta() {
            DetalleVentaRequestDTO dto1 = new DetalleVentaRequestDTO(); dto1.setCantidad(1);
            DetalleVentaRequestDTO dto2 = new DetalleVentaRequestDTO(); dto2.setCantidad(2);
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("equals detecta diferencia en precioUnitario")
        void equalsPrecioDistinto() {
            DetalleVentaRequestDTO dto1 = new DetalleVentaRequestDTO(); dto1.setPrecioUnitario(1000F);
            DetalleVentaRequestDTO dto2 = new DetalleVentaRequestDTO(); dto2.setPrecioUnitario(2000F);
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("null vs valor — productoId")
        void nullVsValorProductoId() {
            DetalleVentaRequestDTO dto1 = new DetalleVentaRequestDTO();
            DetalleVentaRequestDTO dto2 = new DetalleVentaRequestDTO(); dto2.setProductoId(10L);
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("null vs valor — cantidad")
        void nullVsValorCantidad() {
            DetalleVentaRequestDTO dto1 = new DetalleVentaRequestDTO();
            DetalleVentaRequestDTO dto2 = new DetalleVentaRequestDTO(); dto2.setCantidad(2);
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("null vs valor — precioUnitario")
        void nullVsValorPrecio() {
            DetalleVentaRequestDTO dto1 = new DetalleVentaRequestDTO();
            DetalleVentaRequestDTO dto2 = new DetalleVentaRequestDTO(); dto2.setPrecioUnitario(12990F);
            assertNotEquals(dto1, dto2);
        }

        @Test
        @DisplayName("genera toString correctamente")
        void testToString() {
            DetalleVentaRequestDTO dto = new DetalleVentaRequestDTO();
            dto.setProductoId(10L);
            String resultado = dto.toString();
            assertNotNull(resultado);
            assertTrue(resultado.contains("10"));
        }

        @Test
        @DisplayName("toString objeto vacío no lanza excepción")
        void toStringVacio() {
            assertNotNull(new DetalleVentaRequestDTO().toString());
        }
    }
}