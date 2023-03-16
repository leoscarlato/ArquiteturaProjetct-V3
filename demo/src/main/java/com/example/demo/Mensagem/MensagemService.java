package com.example.demo.Mensagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Usuario.Usuario;

import java.util.*;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public List<Mensagem> getAllMensagens() {
        return mensagemRepository.findAll();
    }

    public Mensagem getMensagemById(Integer id) {
        return mensagemRepository.findById(id).orElse(null);
    }

    public MensagemTexto criarMensagemTexto(MensagemTexto mensagem){
        return mensagemRepository.save(mensagem);
    }

    public MensagemArquivo criarMensagemArquivo(MensagemArquivo mensagem){
        return mensagemRepository.save(mensagem);
    }

    public Mensagem getMensagemByUser(Usuario usuario){
        return mensagemRepository.findByUsuario(usuario);
    }
    
}
