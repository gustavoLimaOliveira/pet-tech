package br.com.fiap.pettech.dominio.categoria.service;

import br.com.fiap.pettech.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import br.com.fiap.pettech.dominio.categoria.repository.ICategoriaRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository repository;

    public Page<CategoriaDTO> findAll(PageRequest pageRequest) {
        Page<Categoria> list = repository.findAll(pageRequest);
        return  list.map(x -> new CategoriaDTO(x));
    }

    public CategoriaDTO findById(Long id) {
        Optional<Categoria> entity = repository.findById(id);
        Categoria categoria = entity.orElseThrow(() -> new ControllerNotFoundException("Categoria não encontrada"));
        return  new CategoriaDTO(categoria);
    }

    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        mapperDtoToEntity(dto, entity);
        Categoria categoriaInserted = repository.save(entity);
        return new CategoriaDTO(categoriaInserted);
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try {
            Categoria entity = repository.getOne(id);
            mapperDtoToEntity(dto, entity);
            entity = repository.save(entity);

            return new CategoriaDTO(entity);
        } catch(EntityNotFoundException e) {
            throw new ControllerNotFoundException("Categoria não econtrada" + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ControllerNotFoundException("Categoria não encontrada" + id);
        } catch (DataIntegrityViolationException e) {
            throw  new DatabaseException("Violação de integridade dos dados");
        }

    }

    private void  mapperDtoToEntity(CategoriaDTO dto, Categoria entity) {
        entity.setNome(dto.getNome());
    }
}
