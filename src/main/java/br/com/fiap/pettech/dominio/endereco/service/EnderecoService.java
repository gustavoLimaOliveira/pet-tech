package br.com.fiap.pettech.dominio.endereco.service;

import br.com.fiap.pettech.dominio.endereco.dto.EnderecoDTO;
import br.com.fiap.pettech.dominio.endereco.entitie.Endereco;
import br.com.fiap.pettech.dominio.endereco.repository.IEnderecoRepository;
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
public class EnderecoService {

    private final IEnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(IEnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional(readOnly = true)
    public Page<EnderecoDTO> findAll(PageRequest pageRequest) {
        var enderecos = enderecoRepository.findAll(pageRequest);
        return enderecos.map(EnderecoDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public EnderecoDTO findById(Long id) {
        var endereco = enderecoRepository.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Endereço não encontrado")
        );

        return EnderecoDTO.fromEntity(endereco);
    }

    @Transactional
    public EnderecoDTO save(EnderecoDTO dto) {
        var entity = EnderecoDTO.toEntity(dto);
        var enderecoSaved = enderecoRepository.save(entity);
        return EnderecoDTO.fromEntity(enderecoSaved);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        try {
            Endereco entity = enderecoRepository.getReferenceById(id);
            EnderecoDTO.mapperDtoToEntity(dto, entity);
            entity = enderecoRepository.save(entity);
            return EnderecoDTO.fromEntity(entity);

        }  catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Endereço não encontrado, id: " + id);
        }
    }

    @Transactional
    public void delete(Long id)  {
        try {
            enderecoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade dos dados");
        }
    }

}
