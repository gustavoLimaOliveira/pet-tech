package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IProdutoRepository {

    Optional<Produto> findById(UUID id);

    Set<Produto> findAll();

    Produto save(Produto produto);

    Produto update(UUID id, Produto produto);

    void delete(UUID id);
}
