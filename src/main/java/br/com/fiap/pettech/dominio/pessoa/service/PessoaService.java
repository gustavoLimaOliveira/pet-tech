package br.com.fiap.pettech.dominio.pessoa.service;

import br.com.fiap.pettech.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.dominio.pessoa.repository.IPessoaRepository;
import br.com.fiap.pettech.exception.service.ControllerNotFoundException;
import br.com.fiap.pettech.exception.service.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {

    private final IPessoaRepository repo;

    @Autowired
    public PessoaService(IPessoaRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public Page<PessoaDTO> findAll(PageRequest pageRequest) {
        var enderecos = repo.findAll(pageRequest);
        return enderecos.map(PessoaDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        var endereco = repo.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Endereço não encontrado")
        );

        return PessoaDTO.fromEntity(endereco);
    }

    @Transactional
    public PessoaDTO save(PessoaDTO dto) {
        var entity = PessoaDTO.toEntity(dto);
        var enderecoSaved = repo.save(entity);
        return PessoaDTO.fromEntity(enderecoSaved);
    }

    @Transactional
    public PessoaDTO update(Long id, PessoaDTO dto) {
        try {
            Pessoa entity = repo.getReferenceById(id);
            PessoaDTO.mapperDtoToEntity(dto, entity);
            entity = repo.save(entity);
            return PessoaDTO.fromEntity(entity);

        }  catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Endereço não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id)  {
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

}
