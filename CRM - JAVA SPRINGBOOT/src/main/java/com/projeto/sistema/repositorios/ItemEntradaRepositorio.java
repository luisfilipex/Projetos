package com.projeto.sistema.repositorios;


import com.projeto.sistema.modelos.ItemEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemEntradaRepositorio extends JpaRepository<ItemEntrada, Long> {

}
