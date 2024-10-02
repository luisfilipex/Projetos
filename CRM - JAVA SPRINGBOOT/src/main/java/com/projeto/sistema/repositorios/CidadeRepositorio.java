package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Cidade;
import com.projeto.sistema.modelos.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CidadeRepositorio extends JpaRepository<Cidade, Long> {

}
