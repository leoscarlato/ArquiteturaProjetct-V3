package com.example.demo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario getUsuarioByUsername(String nome) {
        if (usuarioRepository.findByUsername(nome) != null) {
            return usuarioRepository.findByUsername(nome);
        } else{
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public Usuario createUsuario(Usuario usuario) {
        
        return usuarioRepository.save(usuario);
    }
}
