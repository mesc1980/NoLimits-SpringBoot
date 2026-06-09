package com.example.NoLimits.config;

import com.example.NoLimits.Multimedia.config.AdminInitializer;
import com.example.NoLimits.Multimedia.model.usuario.RolModel;
import com.example.NoLimits.Multimedia.model.usuario.UsuarioModel;
import com.example.NoLimits.Multimedia.repository.usuario.RolRepository;
import com.example.NoLimits.Multimedia.repository.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("AdminInitializer")
class AdminInitializerTest {

    private AdminInitializer adminInitializer;
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        rolRepository     = mock(RolRepository.class);
        passwordEncoder   = mock(PasswordEncoder.class);

        adminInitializer = new AdminInitializer();
        ReflectionTestUtils.setField(adminInitializer, "usuarioRepository", usuarioRepository);
        ReflectionTestUtils.setField(adminInitializer, "rolRepository",     rolRepository);
        ReflectionTestUtils.setField(adminInitializer, "passwordEncoder",   passwordEncoder);
    }

    @Nested
    @DisplayName("credenciales vacías o nulas")
    class CredencialesVacias {

        @Test
        @DisplayName("correo en blanco → retorna sin tocar los repos")
        void correoEnBlanco_noHaceNada() throws Exception {
            ReflectionTestUtils.setField(adminInitializer, "correo",   "  ");
            ReflectionTestUtils.setField(adminInitializer, "password", "123456");

            adminInitializer.run();

            verifyNoInteractions(rolRepository, usuarioRepository);
        }

        @Test
        @DisplayName("password en blanco → retorna sin tocar los repos")
        void passwordEnBlanco_noHaceNada() throws Exception {
            ReflectionTestUtils.setField(adminInitializer, "correo",   "admin@test.com");
            ReflectionTestUtils.setField(adminInitializer, "password", "");

            adminInitializer.run();

            verifyNoInteractions(rolRepository, usuarioRepository);
        }
    }

    @Nested
    @DisplayName("rol no encontrado")
    class RolNoEncontrado {

        @Test
        @DisplayName("ROLE_ADMIN y ADMIN inexistentes → retorna sin crear usuario")
        void rolInexistente_noCreaNadaUsuario() throws Exception {
            ReflectionTestUtils.setField(adminInitializer, "correo",   "admin@test.com");
            ReflectionTestUtils.setField(adminInitializer, "password", "secreta");

            when(rolRepository.findByNombreIgnoreCase("ROLE_ADMIN")).thenReturn(Optional.empty());
            when(rolRepository.findByNombreIgnoreCase("ADMIN")).thenReturn(Optional.empty());

            adminInitializer.run();

            verify(usuarioRepository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("admin no existe → se crea")
    class AdminNuevo {

        @Test
        @DisplayName("usuario no existe → se guarda uno nuevo")
        void adminNoExiste_creaUsuario() throws Exception {
            ReflectionTestUtils.setField(adminInitializer, "correo",   "admin@test.com");
            ReflectionTestUtils.setField(adminInitializer, "password", "secreta");

            RolModel rol = new RolModel();
            rol.setId(1L);
            rol.setNombre("ROLE_ADMIN");

            when(rolRepository.findByNombreIgnoreCase("ROLE_ADMIN")).thenReturn(Optional.of(rol));
            when(usuarioRepository.findByCorreoIgnoreCase("admin@test.com")).thenReturn(Optional.empty());
            when(passwordEncoder.encode("secreta")).thenReturn("hash_encriptado");
            when(usuarioRepository.save(any(UsuarioModel.class))).thenAnswer(inv -> inv.getArgument(0));

            adminInitializer.run();

            verify(usuarioRepository).save(any(UsuarioModel.class));
        }
    }

    @Nested
    @DisplayName("admin ya existe → se actualiza")
    class AdminExistente {

        @Test
        @DisplayName("usuario ya existe → se actualiza y se guarda")
        void adminExiste_actualizaDatos() throws Exception {
            ReflectionTestUtils.setField(adminInitializer, "correo",   "admin@test.com");
            ReflectionTestUtils.setField(adminInitializer, "password", "nueva_clave");

            RolModel rol = new RolModel();
            rol.setId(1L);
            rol.setNombre("ROLE_ADMIN");

            UsuarioModel existente = new UsuarioModel();
            existente.setId(99L);
            existente.setCorreo("admin@test.com");

            when(rolRepository.findByNombreIgnoreCase("ROLE_ADMIN")).thenReturn(Optional.of(rol));
            when(usuarioRepository.findByCorreoIgnoreCase("admin@test.com")).thenReturn(Optional.of(existente));
            when(passwordEncoder.encode("nueva_clave")).thenReturn("hash_nuevo");
            when(usuarioRepository.save(any(UsuarioModel.class))).thenAnswer(inv -> inv.getArgument(0));

            adminInitializer.run();

            verify(usuarioRepository).save(existente);
        }
    }
}