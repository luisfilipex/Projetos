package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Cliente;
import com.projeto.sistema.modelos.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FornecedorRepositorio extends JpaRepository<Fornecedor, Long> {

}
