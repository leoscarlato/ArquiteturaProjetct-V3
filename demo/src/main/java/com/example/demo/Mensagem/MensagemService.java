package com.example.demo.Mensagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Usuario.Usuario;
import com.example.demo.Usuario.UsuarioService;

import java.util.*;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Mensagem> getAllMensagens() {
        return mensagemRepository.findAll();
    }

    public Mensagem getMensagemById(Integer id) {
        return mensagemRepository.findById(id).orElse(null);
    }

    public MensagemTexto criarMensagemTexto(Map<String, String> json){
        MensagemTexto mensagem = new MensagemTexto();
        mensagem.setTexto(json.get("texto"));
        mensagem.setUsername(usuarioService.getUsuarioByUsername(json.get("username")));
        return mensagemRepository.save(mensagem);
    }
        
    public MensagemArquivo criarMensagemArquivo(Map<String, String> json){
        MensagemArquivo mensagem = new MensagemArquivo();
        mensagem.setArquivo(json.get("arquivo"));
        mensagem.setUsername(usuarioService.getUsuarioByUsername(json.get("username")));
        return mensagemRepository.save(mensagem);
    }
    
    public List<Mensagem> getMensagemByUser(Usuario usuario){
        return mensagemRepository.findAllByUsername(usuario);
    }
    
    
}
