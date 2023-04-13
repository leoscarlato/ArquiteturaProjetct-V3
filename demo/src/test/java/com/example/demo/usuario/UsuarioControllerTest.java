package com.example.demo.usuario;

import com.example.demo.Usuario.Usuario;
import com.example.demo.Usuario.UsuarioController;
import com.example.demo.Usuario.UsuarioRepository;
import com.example.demo.Usuario.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    void test_todos_usuarios() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setUsername("leo");
        usuario.setId(1);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        Mockito.when(usuarioService.getAllUsuarios()).thenReturn(usuarios);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/usuario"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(usuarios), resp);

    }

    @Test
    void test_usuario_get_by_id() throws Exception {
        
        Usuario usuario = new Usuario();
        usuario.setUsername("leo");
        usuario.setId(1);

        Mockito.when(usuarioService.getUsuarioById(1)).thenReturn(usuario);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/usuario/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(usuario), resp);
    }

    @Test
    void test_cria_usuario() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setUsername("leo");
        usuario.setId(1);

        Mockito.when(usuarioService.createUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);

        ObjectMapper om = new ObjectMapper();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
                .contentType("application/json")
                .content(om.writeValueAsString(usuario)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(usuario), resp);

    }
}
