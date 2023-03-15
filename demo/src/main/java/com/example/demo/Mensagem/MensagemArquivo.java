package com.example.demo.Mensagem;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MensagemArquivo extends Mensagem{
    
    private String nomeArquivo;

}
