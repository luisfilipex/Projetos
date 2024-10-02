package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepositorio extends JpaRepository<Venda, Long> {

}
