package com.example.demo.Mensagem;

import com.example.demo.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
    
    Mensagem findByUsuario(Usuario usuario);
}
