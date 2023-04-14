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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class MensagemServiceTest {

    @InjectMocks
    MensagemService mensagemService;

    @Mock
    MensagemRepository mensagemRepository;

    @Mock
    UsuarioService usuarioService;

    @Mock
    MensagemTexto mensagemTexto;

    @Mock
    MensagemArquivo mensagemArquivo;


    @Test
    void test_todas_mensagens(){
        MensagemTexto mensagem_1 = new MensagemTexto();
        MensagemTexto mensagem_2 = new MensagemTexto();

        mensagem_1.setTexto("teste");
        mensagem_2.setTexto("teste2");

        List<Mensagem> mensagens = new ArrayList<>();

        Mockito.when(mensagemRepository.findAll()).thenReturn(mensagens);

        List<Mensagem> mensagensEncontradas = mensagemService.getAllMensagens();

        Assertions.assertEquals(mensagensEncontradas.size(), mensagens.size());
    }

    @Test
    void test_mensagem_get_by_id(){
        MensagemTexto mensagem_1 = new MensagemTexto();
        mensagem_1.setTexto("teste");
        mensagem_1.setId(1);

        Mockito.when(mensagemRepository.findById(1)).thenReturn(Optional.of(mensagem_1));

        Mensagem mensagemEncontrada = mensagemService.getMensagemById(1);

        Assertions.assertEquals(mensagemEncontrada.getId(), mensagem_1.getId());
    }

    @Test
    void test_mensagem_get_by_username(){
        MensagemTexto mensagem_1 = new MensagemTexto();
        mensagem_1.setTexto("teste");
        mensagem_1.setId(1);

        Usuario usuario = new Usuario();
        usuario.setUsername("teste");
        usuario.setId(1);

        mensagem_1.setUsername(usuario);

        Mockito.when(mensagemRepository.findById(1)).thenReturn(Optional.of(mensagem_1));

        Mensagem mensagemEncontrada = mensagemService.getMensagemById(1);

        Assertions.assertEquals(mensagemEncontrada.getUsername().getId(), usuario.getId());
    }

    @Test
    public void testCriarMensagemTexto() {

        Map<String, String> json = new HashMap<>();
        json.put("texto", "Olá, mundo!");
        json.put("username", "joao");

        Usuario usuario = new Usuario();
        usuario.setUsername("joao");

        Mockito.when(usuarioService.getUsuarioByUsername("joao")).thenReturn(usuario);

        Mockito.when(mensagemRepository.save(Mockito.any(MensagemTexto.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        MensagemTexto mensagemTexto = mensagemService.criarMensagemTexto(json);

        assert mensagemTexto.getTexto().equals("Olá, mundo!");
        assert mensagemTexto.getUsername().equals(usuario);

        Mockito.verify(mensagemRepository).save(mensagemTexto);
    }

    @Test
    public void testCriarMensagemArquivo() {

        Map<String, String> json = new HashMap<>();
        json.put("arquivo", "arquivo.txt");
        json.put("username", "joao");

        Usuario usuario = new Usuario();
        usuario.setUsername("joao");

        Mockito.when(usuarioService.getUsuarioByUsername("joao")).thenReturn(usuario);

        Mockito.when(mensagemRepository.save(Mockito.any(MensagemArquivo.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        MensagemArquivo mensagemArquivo = mensagemService.criarMensagemArquivo(json);

        assert mensagemArquivo.getArquivo().equals("arquivo.txt");
        assert mensagemArquivo.getUsername().equals(usuario);

        Mockito.verify(mensagemRepository).save(mensagemArquivo);
    }
    


    
}
