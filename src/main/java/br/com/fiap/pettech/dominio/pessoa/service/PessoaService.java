package br.com.fiap.pettech.dominio.pessoa.service;

import br.com.fiap.pettech.dominio.pessoa.dto.PessoaEnderecoUsuarioDTO;
import br.com.fiap.pettech.dominio.pessoa.dto.PessoaUsuarioDTO;
import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.dominio.pessoa.repository.IPessoaRepository;
import br.com.fiap.pettech.dominio.usuario.repository.IUsuarioRepository;
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

    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public PessoaService(IPessoaRepository repo, IUsuarioRepository usuarioRepository) {
        this.repo = repo;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Page<PessoaEnderecoUsuarioDTO> findAll(PageRequest pageRequest) {
        var enderecos = repo.findAll(pageRequest);
        return enderecos.map(PessoaEnderecoUsuarioDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public PessoaEnderecoUsuarioDTO findById(Long id) {
        var endereco = repo.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Endereço não encontrado")
        );

        return PessoaEnderecoUsuarioDTO.fromEntity(endereco);
    }

    @Transactional
    public PessoaUsuarioDTO save(PessoaUsuarioDTO dto) {
        try {
            var usuario = usuarioRepository.getReferenceById(dto.usuario().id());
            var entity = PessoaUsuarioDTO.toEntity(dto, usuario);
            var enderecoSaved = repo.save(entity);
            return PessoaUsuarioDTO.fromEntity(enderecoSaved);
        } catch (DataIntegrityViolationException e) {
            throw  new DatabaseException("Usuário não encontrado");
        }

    }

    @Transactional
    public PessoaUsuarioDTO update(Long id, PessoaUsuarioDTO dto) {
        try {
            var usuario = usuarioRepository.getReferenceById(dto.usuario().id());
            Pessoa entity = repo.getReferenceById(id);
            PessoaUsuarioDTO.mapperDtoToEntity(dto, entity, usuario);
            entity = repo.save(entity);
            return PessoaUsuarioDTO.fromEntity(entity);

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
