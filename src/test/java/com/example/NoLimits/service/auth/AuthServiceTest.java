package com.example.NoLimits.service.auth;

import com.example.NoLimits.Multimedia.model.usuario.RolModel;
import com.example.NoLimits.Multimedia.model.usuario.UsuarioModel;
import com.example.NoLimits.Multimedia.repository.usuario.RolRepository;
import com.example.NoLimits.Multimedia.repository.usuario.UsuarioRepository;
import com.example.NoLimits.Multimedia.security.JwtUtil;
import com.example.NoLimits.config.AbstractContainerBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthServiceTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private RolRepository rolRepository;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private UsuarioModel usuarioConRol() {
        RolModel rol = new RolModel();
        rol.setId(1L);
        rol.setNombre("ROLE_USER");

        UsuarioModel u = new UsuarioModel();
        u.setId(1L);
        u.setNombre("Ana");
        u.setApellidos("González");
        u.setCorreo("ana@test.com");
        u.setPassword("hashed_pass");
        u.setRol(rol);
        return u;
    }

    @Nested
    @DisplayName("POST /api/v1/auth/login")
    class Login {

        @Test
        @DisplayName("retorna 200 con token cuando las credenciales son correctas")
        void retorna200ConToken() throws Exception {
            UsuarioModel usuario = usuarioConRol();
            when(usuarioRepository.findByCorreoIgnoreCase("ana@test.com")).thenReturn(Optional.of(usuario));
            when(passwordEncoder.matches("password123", "hashed_pass")).thenReturn(true);
            when(jwtUtil.generateToken("ana@test.com", "ROLE_USER")).thenReturn("jwt-token-generado");

            mockMvc.perform(post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"correo\":\"ana@test.com\",\"password\":\"password123\"}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.token").value("jwt-token-generado"))
                    .andExpect(jsonPath("$.correo").value("ana@test.com"))
                    .andExpect(jsonPath("$.rolNombre").value("ROLE_USER"));
        }

        @Test
        @DisplayName("retorna 401 si la contraseña es incorrecta")
        void retorna401ConPasswordIncorrecto() throws Exception {
            UsuarioModel usuario = usuarioConRol();
            when(usuarioRepository.findByCorreoIgnoreCase("ana@test.com")).thenReturn(Optional.of(usuario));
            when(passwordEncoder.matches("wrong_pass", "hashed_pass")).thenReturn(false);

            mockMvc.perform(post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"correo\":\"ana@test.com\",\"password\":\"wrong_pass\"}"))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("retorna 401 si el usuario no existe")
        void retorna401SiUsuarioNoExiste() throws Exception {
            when(usuarioRepository.findByCorreoIgnoreCase("noexiste@test.com")).thenReturn(Optional.empty());

            mockMvc.perform(post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"correo\":\"noexiste@test.com\",\"password\":\"pass\"}"))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("retorna 400 si faltan correo o password")
        void retorna400SiFaltanCampos() throws Exception {
            mockMvc.perform(post("/api/v1/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"correo\":\"ana@test.com\"}"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("POST /api/v1/auth/reset-password")
    class ResetPassword {

        @Test
        @DisplayName("retorna 200 al cambiar contraseña de usuario existente")
        void retorna200AlCambiarPassword() throws Exception {
            UsuarioModel usuario = usuarioConRol();
            when(usuarioRepository.findByCorreoIgnoreCase("ana@test.com")).thenReturn(Optional.of(usuario));
            when(passwordEncoder.encode("nueva_pass")).thenReturn("nueva_pass_hashed");
            when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(usuario);

            mockMvc.perform(post("/api/v1/auth/reset-password")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"correo\":\"ana@test.com\",\"password\":\"nueva_pass\"}"))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("retorna 404 si el correo no existe")
        void retorna404SiCorreoNoExiste() throws Exception {
            when(usuarioRepository.findByCorreoIgnoreCase("fantasma@test.com")).thenReturn(Optional.empty());

            mockMvc.perform(post("/api/v1/auth/reset-password")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"correo\":\"fantasma@test.com\",\"password\":\"algo\"}"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("POST /api/v1/auth/logout")
    class Logout {

        @Test
        @DisplayName("retorna 200 al hacer logout")
        void retorna200AlLogout() throws Exception {
            mockMvc.perform(post("/api/v1/auth/logout"))
                    .andExpect(status().isOk());
        }
    }
}