package com.example.demo.Mensagem;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MensagemArquivo extends Mensagem{
    
    private String arquivo;

}
