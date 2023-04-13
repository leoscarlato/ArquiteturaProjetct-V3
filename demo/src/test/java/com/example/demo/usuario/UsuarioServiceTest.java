package com.example.demo.usuario;

import com.example.demo.Usuario.Usuario;
import com.example.demo.Usuario.UsuarioRepository;
import com.example.demo.Usuario.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Test
    void test_cria_usuario() {
        Usuario usuario = new Usuario();
        usuario.setUsername("teste");
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario usuarioCriado = usuarioService.createUsuario(usuario);
        Assertions.assertEquals(usuarioCriado.getUsername(), usuario.getUsername());
    }

    @Test
    void test_todos_usuarios(){
        Usuario usuario_1 = new Usuario();
        Usuario usuario_2 = new Usuario();

        usuario_1.setUsername("leo");
        usuario_2.setUsername("ale");

        List<Usuario> usuarios = new ArrayList<>();

        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> usuariosEncontrados = usuarioService.getAllUsuarios();
        
        Assertions.assertEquals(usuariosEncontrados.size(), usuarios.size());
    }

    @Test
    void test_usuario_get_by_id(){
        Usuario usuario_1 = new Usuario();
        usuario_1.setUsername("leo");
        usuario_1.setId(1);

        Mockito.when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario_1));

        Usuario usuarioEncontrado = usuarioService.getUsuarioById(1);

        Assertions.assertEquals(usuarioEncontrado.getUsername(), usuario_1.getUsername());
    }

    @Test
    void test_usuario_get_by_username(){
        Usuario usuario_1 = new Usuario();
        usuario_1.setUsername("leo");

        Mockito.when(usuarioRepository.findByUsername(usuario_1.getUsername())).thenReturn(usuario_1);

        Usuario usuarioEncontrado = usuarioService.getUsuarioByUsername("leo");

        Assertions.assertEquals(usuarioEncontrado.getUsername(), usuario_1.getUsername());
    }

    
}
