package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repo;


    public Collection<Produto> findAll() {
        var produtos = repo.findAll();

        return produtos;
    }

    public Produto findById(UUID id) {
        var produto = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return produto;
    }

    public Produto save(Produto produto) {
        var produtoSaved = repo.save(produto);
        return produtoSaved;
    }

    public Produto update(UUID id, Produto produto) {
        try {
            Produto buscaproduto = repo.getOne(id);
            buscaproduto.setNome(produto.getNome());
            buscaproduto.setDescricao(produto.getDescricao());
            buscaproduto.setUrlImagem(produto.getUrlImagem());
            buscaproduto.setPreco(produto.getPreco());
            buscaproduto = repo.save(buscaproduto);

            return buscaproduto;
        } catch (EntityNotFoundException e) {
            throw  new ControllerNotFoundException("Produto não encontrado, id:" + id);
        }
    }

    public void delete(UUID id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entity not found with ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade da base");
        }

    }


}
