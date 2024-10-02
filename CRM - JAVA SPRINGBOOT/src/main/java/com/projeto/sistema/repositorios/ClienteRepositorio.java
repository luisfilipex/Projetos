package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Cliente;
import com.projeto.sistema.modelos.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
