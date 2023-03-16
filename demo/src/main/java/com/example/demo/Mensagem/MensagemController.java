package com.example.demo.Mensagem;

import com.example.demo.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {
    
    @Autowired
    private MensagemService mensagemService;

    @GetMapping
    public List<Mensagem> getAllMensagens() {
        return mensagemService.getAllMensagens();
    }

    @PostMapping("/texto")
    public MensagemTexto criarMensagemTexto(@RequestBody Map<String, String> json){
        return mensagemService.criarMensagemTexto(json);
    }

    @PostMapping("/arquivo")
    public MensagemArquivo criarMensagemArquivo(@RequestBody Map<String, String> json){
        return mensagemService.criarMensagemArquivo(json);
    }

    @GetMapping("/{id}")
    public Mensagem getMensagemByUser(@PathVariable Usuario usuario){
        return mensagemService.getMensagemByUser(usuario);
    }
}
