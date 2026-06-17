package com.example.NoLimits.model.usuario;

import com.example.NoLimits.Multimedia.model.usuario.UsuarioModel;
import com.example.NoLimits.Multimedia.model.usuario.RolModel;
import com.example.NoLimits.Multimedia.model.ubicacion.ComunaModel;
import com.example.NoLimits.Multimedia.model.ubicacion.DireccionModel;
import com.example.NoLimits.Multimedia.model.ubicacion.RegionModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UsuarioModel Tests")
class UsuarioModelTest {

    @Nested
    @DisplayName("Nombre completo")
    class NombreCompleto {

        @Test
        void debeRetornarNombreCompleto() {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setNombre("Juan");
            usuario.setApellidos("Pérez Soto");
            assertEquals("Juan Pérez Soto", usuario.getNombreCompleto());
        }

        @Test
        void debeManejarNombreNull() {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setApellidos("Pérez");
            assertEquals(" Pérez", usuario.getNombreCompleto());
        }

        @Test
        void debeManejarApellidosNull() {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setNombre("Juan");
            assertEquals("Juan ", usuario.getNombreCompleto());
        }
    }

    @Nested
    @DisplayName("Direccion")
    class Direccion {

        @Test
        void debeRetornarDireccionId() {
            DireccionModel direccion = new DireccionModel();
            direccion.setId(10L);
            UsuarioModel usuario = new UsuarioModel();
            usuario.setDireccion(direccion);
            assertEquals(10L, usuario.getDireccionId());
        }

        @Test
        void direccionNullRetornaNull() {
            UsuarioModel usuario = new UsuarioModel();
            assertNull(usuario.getDireccionId());
        }
    }

    @Nested
    @DisplayName("Comuna")
    class Comuna {

        @Test
        void debeRetornarDatosComuna() {
            ComunaModel comuna = new ComunaModel();
            comuna.setId(13101L);
            comuna.setNombre("Santiago");
            DireccionModel direccion = new DireccionModel();
            direccion.setComuna(comuna);
            UsuarioModel usuario = new UsuarioModel();
            usuario.setDireccion(direccion);
            assertEquals(13101L, usuario.getComunaId());
            assertEquals("Santiago", usuario.getComunaNombre());
        }

        @Test
        void comunaNullRetornaNull() {
            DireccionModel direccion = new DireccionModel();
            UsuarioModel usuario = new UsuarioModel();
            usuario.setDireccion(direccion);
            assertNull(usuario.getComunaId());
            assertNull(usuario.getComunaNombre());
        }
    }

    @Nested
    @DisplayName("Region")
    class Region {

        @Test
        void debeRetornarDatosRegion() {
            RegionModel region = new RegionModel();
            region.setId(13L);
            region.setNombre("Metropolitana");
            ComunaModel comuna = new ComunaModel();
            comuna.setRegion(region);
            DireccionModel direccion = new DireccionModel();
            direccion.setComuna(comuna);
            UsuarioModel usuario = new UsuarioModel();
            usuario.setDireccion(direccion);
            assertEquals(13L, usuario.getRegionId());
            assertEquals("Metropolitana", usuario.getRegionNombre());
        }

        @Test
        void regionNullRetornaNull() {
            ComunaModel comuna = new ComunaModel();
            DireccionModel direccion = new DireccionModel();
            direccion.setComuna(comuna);
            UsuarioModel usuario = new UsuarioModel();
            usuario.setDireccion(direccion);
            assertNull(usuario.getRegionId());
            assertNull(usuario.getRegionNombre());
        }
    }

    @Nested
    @DisplayName("Getters y Setters")
    class GettersSetters {

        @Test
        void debeAsignarPropiedades() {
            RolModel rol = new RolModel();
            UsuarioModel usuario = new UsuarioModel();
            usuario.setId(1L);
            usuario.setNombre("Juan");
            usuario.setApellidos("Pérez");
            usuario.setCorreo("juan@test.cl");
            usuario.setTelefono(123456789L);
            usuario.setFotoPerfil("foto.jpg");
            usuario.setPassword("12345678");
            usuario.setRol(rol);
            assertEquals(1L, usuario.getId());
            assertEquals("Juan", usuario.getNombre());
            assertEquals("Pérez", usuario.getApellidos());
            assertEquals("juan@test.cl", usuario.getCorreo());
            assertEquals(123456789L, usuario.getTelefono());
            assertEquals("foto.jpg", usuario.getFotoPerfil());
            assertEquals("12345678", usuario.getPassword());
            assertEquals(rol, usuario.getRol());
        }

        @Test
        @DisplayName("setVentas y getVentas funcionan")
        void setVentasGetVentas() {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setVentas(new ArrayList<>());
            assertNotNull(usuario.getVentas());
            assertTrue(usuario.getVentas().isEmpty());
        }

        @Test
        @DisplayName("ventas null por defecto")
        void ventasNullPorDefecto() {
            UsuarioModel usuario = new UsuarioModel();
            assertNull(usuario.getVentas());
        }
    }

    @Nested
    @DisplayName("Constructor AllArgs")
    class ConstructorAllArgs {

        @Test
        @DisplayName("constructor con todos los parámetros funciona")
        void testAllArgsConstructor() {
            RolModel rol = new RolModel();
            DireccionModel direccion = new DireccionModel();

            UsuarioModel usuario = new UsuarioModel(
                    1L, "Juan", "Pérez", "juan@test.cl",
                    987654321L, "foto.jpg", "clave123",
                    rol, direccion, null
            );

            assertEquals(1L, usuario.getId());
            assertEquals("Juan", usuario.getNombre());
            assertEquals("Pérez", usuario.getApellidos());
            assertEquals("juan@test.cl", usuario.getCorreo());
            assertEquals(987654321L, usuario.getTelefono());
            assertEquals("foto.jpg", usuario.getFotoPerfil());
            assertEquals("clave123", usuario.getPassword());
            assertEquals(rol, usuario.getRol());
            assertEquals(direccion, usuario.getDireccion());
            assertNull(usuario.getVentas());
        }
    }

    @Nested
    @DisplayName("Equals y ToString")
    class EqualsToString {

        @Test
        void objetosConMismoContenidoSonIguales() {
            UsuarioModel u1 = new UsuarioModel(); u1.setId(1L);
            UsuarioModel u2 = new UsuarioModel(); u2.setId(1L);
            assertEquals(u1, u2);
            assertEquals(u1.hashCode(), u2.hashCode());
        }

        @Test
        void toStringNoDebeSerNull() {
            assertNotNull(new UsuarioModel().toString());
        }

        @Test
        void equalsMismaInstancia() {
            UsuarioModel u = new UsuarioModel(); u.setId(1L);
            assertEquals(u, u);
        }

        @Test
        void equalsConNull() {
            assertNotEquals(null, new UsuarioModel());
        }

        @Test
        void equalsConOtraClase() {
            assertNotEquals("texto", new UsuarioModel());
        }

        @Test
        void objetosVaciosSonIguales() {
            assertEquals(new UsuarioModel(), new UsuarioModel());
        }

        @Test
        void equalsIdDistinto() {
            UsuarioModel u1 = new UsuarioModel(); u1.setId(1L);
            UsuarioModel u2 = new UsuarioModel(); u2.setId(2L);
            assertNotEquals(u1, u2);
        }

        @Test
        void nullVsValorNombre() {
            UsuarioModel u1 = new UsuarioModel();
            UsuarioModel u2 = new UsuarioModel(); u2.setNombre("Juan");
            assertNotEquals(u1, u2);
        }

        @Test
        void nullVsValorCorreo() {
            UsuarioModel u1 = new UsuarioModel();
            UsuarioModel u2 = new UsuarioModel(); u2.setCorreo("juan@test.cl");
            assertNotEquals(u1, u2);
        }

        @Test
        void nullVsValorApellidos() {
            UsuarioModel u1 = new UsuarioModel();
            UsuarioModel u2 = new UsuarioModel(); u2.setApellidos("Pérez");
            assertNotEquals(u1, u2);
        }

        @Test
        void nullVsValorTelefono() {
            UsuarioModel u1 = new UsuarioModel();
            UsuarioModel u2 = new UsuarioModel(); u2.setTelefono(123456789L);
            assertNotEquals(u1, u2);
        }
    }

    @Nested
    @DisplayName("getNombreCompleto — ramas null")
    class NombreCompletoNull {

        @Test
        @DisplayName("nombre null → resultado contiene apellidos")
        void nombreNull_contieneApellidos() {
            UsuarioModel u = new UsuarioModel();
            u.setNombre(null);
            u.setApellidos("Pérez");
            assertTrue(u.getNombreCompleto().contains("Pérez"));
        }

        @Test
        @DisplayName("apellidos null → resultado contiene nombre")
        void apellidosNull_contieneNombre() {
            UsuarioModel u = new UsuarioModel();
            u.setNombre("Juan");
            u.setApellidos(null);
            assertTrue(u.getNombreCompleto().contains("Juan"));
        }

        @Test
        @DisplayName("ambos null → no lanza excepción")
        void ambosNull_noLanzaExcepcion() {
            UsuarioModel u = new UsuarioModel();
            u.setNombre(null);
            u.setApellidos(null);
            assertNotNull(u.getNombreCompleto());
        }
    }

    @Nested
    @DisplayName("getRol — ramas null")
    class RolNull {

        @Test
        @DisplayName("sin rol → getRol() es null")
        void sinRol_esNull() {
            assertNull(new UsuarioModel().getRol());
        }

        @Test
        @DisplayName("con rol → getRol() retorna el rol")
        void conRol_retornaRol() {
            RolModel rol = new RolModel();
            rol.setId(1L);
            rol.setNombre("ROLE_USER");
            UsuarioModel u = new UsuarioModel();
            u.setRol(rol);
            assertEquals("ROLE_USER", u.getRol().getNombre());
        }
    }

    @Nested
    @DisplayName("getComunaId / getRegionId — cadena de nulls")
    class CadenaNullsUbicacion {

        @Test
        @DisplayName("direccion no null pero comuna null → getComunaId retorna null")
        void direccionNoNull_comunaNull_getComunaIdEsNull() {
            DireccionModel direccion = new DireccionModel();
            direccion.setComuna(null);
            UsuarioModel u = new UsuarioModel();
            u.setDireccion(direccion);
            assertNull(u.getComunaId());
            assertNull(u.getComunaNombre());
        }

        @Test
        @DisplayName("comuna no null, region null → getRegionId retorna null")
        void comunaNoNull_regionNull_getRegionIdEsNull() {
            ComunaModel comuna = new ComunaModel();
            comuna.setRegion(null);
            DireccionModel direccion = new DireccionModel();
            direccion.setComuna(comuna);
            UsuarioModel u = new UsuarioModel();
            u.setDireccion(direccion);
            assertNull(u.getRegionId());
            assertNull(u.getRegionNombre());
        }

        @Test
        @DisplayName("direccion null → todas las propiedades de ubicación son null")
        void direccionNull_todasLasPropiedadesUbicacionNull() {
            UsuarioModel u = new UsuarioModel();
            u.setDireccion(null);
            assertNull(u.getDireccionId());
            assertNull(u.getComunaId());
            assertNull(u.getComunaNombre());
            assertNull(u.getRegionId());
            assertNull(u.getRegionNombre());
        }
    }
}