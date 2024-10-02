package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EstadoRepositorio extends JpaRepository<Estado, Long> {

}
