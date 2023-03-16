package com.example.demo.Mensagem;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MensagemTexto extends Mensagem{

    private String texto;
    
}
