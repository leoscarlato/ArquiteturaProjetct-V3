package com.example.demo.Usuario;

import com.example.demo.Mensagem.Mensagem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @JsonIgnore
    @OneToMany(mappedBy = "username")
    private List<Mensagem> mensagens;
    

}
