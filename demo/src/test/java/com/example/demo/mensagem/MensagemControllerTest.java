package com.example.demo.mensagem;

import com.example.demo.Usuario.*;
import com.example.demo.Mensagem.*;
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
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class MensagemControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    MensagemController mensagemController;

    @Mock
    MensagemService mensagemService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mensagemController).build();
    }

    @Test
    void test_criar_mensagem_texto() throws Exception{

        Map<String, String> json = new HashMap<>();
        json.put("texto", "teste");
        json.put("usuario", "1");

        Usuario usuario = new Usuario();
        usuario.setId(1);

        MensagemTexto mensagem = new MensagemTexto();
        mensagem.setTexto("teste");
        mensagem.setUsername(usuario);

        Mockito.when(mensagemService.criarMensagemTexto(json)).thenReturn(mensagem);

        ObjectMapper om = new ObjectMapper();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/mensagem/texto")
            .contentType("application/json")
            .content(om.writeValueAsString(json)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mensagem), content);
    }

    @Test
    void test_criar_mensagem_arquivo() throws Exception{
            
            Map<String, String> json = new HashMap<>();
            json.put("arquivo", "teste.py");
            json.put("usuario", "1");
    
            Usuario usuario = new Usuario();
            usuario.setId(1);
    
            MensagemArquivo mensagem = new MensagemArquivo();
            mensagem.setArquivo("teste.py");
            mensagem.setUsername(usuario);
    
            Mockito.when(mensagemService.criarMensagemArquivo(json)).thenReturn(mensagem);
    
            ObjectMapper om = new ObjectMapper();
    
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/mensagem/arquivo")
                .contentType("application/json")
                .content(om.writeValueAsString(json)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    
            String content = result.getResponse().getContentAsString();
            Assertions.assertEquals(om.writeValueAsString(mensagem), content);
    }

    @Test
    void test_todas_mensagens() throws Exception{

        Usuario usuario = new Usuario();
        usuario.setId(1);

        MensagemTexto mensagem = new MensagemTexto();
        mensagem.setTexto("teste");
        mensagem.setUsername(usuario);

        List<Mensagem> mensagens = new ArrayList<>();
        mensagens.add(mensagem);

        Mockito.when(mensagemService.getAllMensagens()).thenReturn(mensagens);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/mensagem"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        Assertions.assertEquals(1, om.readValue(result.getResponse().getContentAsString(), List.class).size());
    }

    @Test
    void test_mensagem_por_id() throws Exception{

        Usuario usuario = new Usuario();
        usuario.setId(1);

        MensagemTexto mensagem = new MensagemTexto();
        mensagem.setTexto("teste");
        mensagem.setUsername(usuario);

        Mockito.when(mensagemService.getMensagemById(1)).thenReturn(mensagem);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/mensagem/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        Assertions.assertEquals(om.writeValueAsString(mensagem), result.getResponse().getContentAsString());
    }

    @Test
    void test_mensagem_por_usuario() throws Exception{

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setUsername("leo");

        MensagemTexto mensagem = new MensagemTexto();
        mensagem.setTexto("teste");
        mensagem.setUsername(usuario);

        List<Mensagem> mensagens = new ArrayList<>();
        mensagens.add(mensagem);

        Mockito.when(mensagemService.getMensagemByUser(Mockito.any(Usuario.class))).thenReturn(mensagens);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/mensagem/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        Assertions.assertEquals(1, om.readValue(result.getResponse().getContentAsString(), List.class).size());

    }
    
}
