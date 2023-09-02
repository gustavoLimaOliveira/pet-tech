package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import br.com.fiap.pettech.dominio.categoria.repository.ICategoriaRepository;
import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.exception.service.ControllerNotFoundException;
import br.com.fiap.pettech.exception.service.DatabaseException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repo;

    @Autowired
    private ICategoriaRepository categoriaRepo;


    @Transactional(readOnly = true)
    public Page<ProdutoDTO> findAll(PageRequest pagina) {
        var produtos = repo.findAll(pagina);

        return produtos.map(prod ->  new ProdutoDTO(prod, prod.getCategorias()));
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(UUID id) {
        var produto = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return new ProdutoDTO(produto, produto.getCategorias());
    }

    @Transactional
    public ProdutoDTO save(ProdutoDTO produto) {
        Produto entity = new Produto();
        mapperDtoToEntity(produto, entity);
        var produtoSaved = repo.save(entity);
        return new ProdutoDTO(produtoSaved, produtoSaved.getCategorias());
    }

    @Transactional
    public ProdutoDTO update(UUID id, ProdutoDTO produto) {
        try {
            Produto entity = repo.getOne(id);
            mapperDtoToEntity(produto, entity);
            return new ProdutoDTO(entity, entity.getCategorias());
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

    private void mapperDtoToEntity(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());
        entity.setUrlImagem(dto.getUrlImagem());
        entity.setDescricao(dto.getDescricao());
        entity.getCategorias().clear();

        for (CategoriaDTO categoriaDTO: dto.getCategorias()) {
            Categoria categoria = categoriaRepo.getOne(categoriaDTO.getId());
            entity.getCategorias().add(categoria);
        }
    }


}
