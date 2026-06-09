package com.example.NoLimits.config;

import com.example.NoLimits.Multimedia.config.AdminInitializer;
import com.example.NoLimits.Multimedia.model.usuario.RolModel;
import com.example.NoLimits.Multimedia.model.usuario.UsuarioModel;
import com.example.NoLimits.Multimedia.repository.usuario.RolRepository;
import com.example.NoLimits.Multimedia.repository.usuario.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("AdminInitializer")
class AdminInitializerTest {

    @Nested
    @DisplayName("describe: run")
    class Run {

        @Test
        @DisplayName("it: no debería crear admin cuando correo está vacío")
        void noDeberiaCrearAdminCuandoCorreoEstaVacio() throws Exception {
            AdminInitializer initializer = crearInitializer("", "admin123");

            initializer.run();

            UsuarioRepository usuarioRepository =
                    (UsuarioRepository) ReflectionTestUtils.getField(initializer, "usuarioRepository");
            RolRepository rolRepository =
                    (RolRepository) ReflectionTestUtils.getField(initializer, "rolRepository");

            verifyNoInteractions(usuarioRepository);
            verifyNoInteractions(rolRepository);
        }

        @Test
        @DisplayName("it: no debería crear admin cuando password está vacío")
        void noDeberiaCrearAdminCuandoPasswordEstaVacio() throws Exception {
            AdminInitializer initializer = crearInitializer("admin@test.com", "");

            initializer.run();

            UsuarioRepository usuarioRepository =
                    (UsuarioRepository) ReflectionTestUtils.getField(initializer, "usuarioRepository");
            RolRepository rolRepository =
                    (RolRepository) ReflectionTestUtils.getField(initializer, "rolRepository");

            verifyNoInteractions(usuarioRepository);
            verifyNoInteractions(rolRepository);
        }

        @Test
        @DisplayName("it: no debería crear admin cuando no existe ROLE_ADMIN ni ADMIN")
        void noDeberiaCrearAdminCuandoNoExisteRolAdmin() throws Exception {
            AdminInitializer initializer = crearInitializer("admin@test.com", "admin123");

            RolRepository rolRepository =
                    (RolRepository) ReflectionTestUtils.getField(initializer, "rolRepository");
            UsuarioRepository usuarioRepository =
                    (UsuarioRepository) ReflectionTestUtils.getField(initializer, "usuarioRepository");

            when(rolRepository.findByNombreIgnoreCase("ROLE_ADMIN"))
                    .thenReturn(Optional.empty());
            when(rolRepository.findByNombreIgnoreCase("ADMIN"))
                    .thenReturn(Optional.empty());

            initializer.run();

            verify(rolRepository).findByNombreIgnoreCase("ROLE_ADMIN");
            verify(rolRepository).findByNombreIgnoreCase("ADMIN");
            verifyNoInteractions(usuarioRepository);
        }

        @Test
        @DisplayName("it: debería crear admin nuevo cuando no existe usuario y existe ROLE_ADMIN")
        void deberiaCrearAdminNuevoCuandoNoExisteUsuarioYExisteRoleAdmin() throws Exception {
            AdminInitializer initializer = crearInitializer(" Admin@Test.com ", "admin123");

            RolRepository rolRepository =
                    (RolRepository) ReflectionTestUtils.getField(initializer, "rolRepository");
            UsuarioRepository usuarioRepository =
                    (UsuarioRepository) ReflectionTestUtils.getField(initializer, "usuarioRepository");
            PasswordEncoder passwordEncoder =
                    (PasswordEncoder) ReflectionTestUtils.getField(initializer, "passwordEncoder");

            RolModel adminRol = new RolModel();
            adminRol.setNombre("ROLE_ADMIN");

            when(rolRepository.findByNombreIgnoreCase("ROLE_ADMIN"))
                    .thenReturn(Optional.of(adminRol));
            when(usuarioRepository.findByCorreoIgnoreCase("admin@test.com"))
                    .thenReturn(Optional.empty());
            when(passwordEncoder.encode("admin123"))
                    .thenReturn("password-encriptada");

            initializer.run();

            ArgumentCaptor<UsuarioModel> usuarioCaptor = ArgumentCaptor.forClass(UsuarioModel.class);
            verify(usuarioRepository).save(usuarioCaptor.capture());

            UsuarioModel adminGuardado = usuarioCaptor.getValue();

            assertEquals("Admin", adminGuardado.getNombre());
            assertEquals("NoLimits", adminGuardado.getApellidos());
            assertEquals("admin@test.com", adminGuardado.getCorreo());
            assertEquals(911111111L, adminGuardado.getTelefono());
            assertEquals("password-encriptada", adminGuardado.getPassword());
            assertEquals(adminRol, adminGuardado.getRol());
        }

        @Test
        @DisplayName("it: debería actualizar admin existente cuando ya existe usuario")
        void deberiaActualizarAdminExistenteCuandoYaExisteUsuario() throws Exception {
            AdminInitializer initializer = crearInitializer("admin@test.com", "admin123");

            RolRepository rolRepository =
                    (RolRepository) ReflectionTestUtils.getField(initializer, "rolRepository");
            UsuarioRepository usuarioRepository =
                    (UsuarioRepository) ReflectionTestUtils.getField(initializer, "usuarioRepository");
            PasswordEncoder passwordEncoder =
                    (PasswordEncoder) ReflectionTestUtils.getField(initializer, "passwordEncoder");

            RolModel adminRol = new RolModel();
            adminRol.setNombre("ROLE_ADMIN");

            UsuarioModel adminExistente = new UsuarioModel();
            adminExistente.setNombre("Nombre antiguo");
            adminExistente.setApellidos("Apellido antiguo");
            adminExistente.setCorreo("admin@test.com");

            when(rolRepository.findByNombreIgnoreCase("ROLE_ADMIN"))
                    .thenReturn(Optional.of(adminRol));
            when(usuarioRepository.findByCorreoIgnoreCase("admin@test.com"))
                    .thenReturn(Optional.of(adminExistente));
            when(passwordEncoder.encode("admin123"))
                    .thenReturn("password-actualizada");

            initializer.run();

            verify(usuarioRepository).save(adminExistente);

            assertEquals("Admin", adminExistente.getNombre());
            assertEquals("NoLimits", adminExistente.getApellidos());
            assertEquals("admin@test.com", adminExistente.getCorreo());
            assertEquals(911111111L, adminExistente.getTelefono());
            assertEquals("password-actualizada", adminExistente.getPassword());
            assertEquals(adminRol, adminExistente.getRol());
        }

        @Test
        @DisplayName("it: debería usar ADMIN como respaldo cuando no existe ROLE_ADMIN")
        void deberiaUsarAdminComoRespaldoCuandoNoExisteRoleAdmin() throws Exception {
            AdminInitializer initializer = crearInitializer("admin@test.com", "admin123");

            RolRepository rolRepository =
                    (RolRepository) ReflectionTestUtils.getField(initializer, "rolRepository");
            UsuarioRepository usuarioRepository =
                    (UsuarioRepository) ReflectionTestUtils.getField(initializer, "usuarioRepository");
            PasswordEncoder passwordEncoder =
                    (PasswordEncoder) ReflectionTestUtils.getField(initializer, "passwordEncoder");

            RolModel adminRol = new RolModel();
            adminRol.setNombre("ADMIN");

            when(rolRepository.findByNombreIgnoreCase("ROLE_ADMIN"))
                    .thenReturn(Optional.empty());
            when(rolRepository.findByNombreIgnoreCase("ADMIN"))
                    .thenReturn(Optional.of(adminRol));
            when(usuarioRepository.findByCorreoIgnoreCase("admin@test.com"))
                    .thenReturn(Optional.empty());
            when(passwordEncoder.encode("admin123"))
                    .thenReturn("password-encriptada");

            initializer.run();

            ArgumentCaptor<UsuarioModel> usuarioCaptor = ArgumentCaptor.forClass(UsuarioModel.class);
            verify(usuarioRepository).save(usuarioCaptor.capture());

            UsuarioModel adminGuardado = usuarioCaptor.getValue();

            assertEquals("admin@test.com", adminGuardado.getCorreo());
            assertEquals(adminRol, adminGuardado.getRol());
        }
    }

    private AdminInitializer crearInitializer(String correo, String password) {
        AdminInitializer initializer = new AdminInitializer();

        ReflectionTestUtils.setField(initializer, "usuarioRepository", mock(UsuarioRepository.class));
        ReflectionTestUtils.setField(initializer, "rolRepository", mock(RolRepository.class));
        ReflectionTestUtils.setField(initializer, "passwordEncoder", mock(PasswordEncoder.class));
        ReflectionTestUtils.setField(initializer, "correo", correo);
        ReflectionTestUtils.setField(initializer, "password", password);

        return initializer;
    }
}