package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepositorio extends JpaRepository<Entrada, Long> {

}
