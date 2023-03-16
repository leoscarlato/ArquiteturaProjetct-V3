package com.example.demo.Mensagem;

import com.example.demo.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
    List<Mensagem> findAllByUsername(Usuario username);

}
