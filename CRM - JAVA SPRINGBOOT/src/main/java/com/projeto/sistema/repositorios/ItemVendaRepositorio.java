package com.projeto.sistema.repositorios;


import com.projeto.sistema.modelos.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemVendaRepositorio extends JpaRepository<ItemVenda, Long> {
    @Query("SELECT e FROM ItemVenda e WHERE e.venda.id = ?1")
    List<ItemVenda> buscarPorVenda(Long id);
}
