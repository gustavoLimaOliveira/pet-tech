package br.com.fiap.pettech.dominio.usuario.service;
import br.com.fiap.pettech.dominio.usuario.dto.UsuarioDTO;
import br.com.fiap.pettech.dominio.usuario.dto.UsuarioPessoaDTO;
import br.com.fiap.pettech.dominio.usuario.entitie.Usuario;
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
public class UsuarioService {

    private final IUsuarioRepository repo;

    @Autowired
    public UsuarioService(IUsuarioRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public Page<UsuarioPessoaDTO> findAll(PageRequest pageRequest) {
        var enderecos = repo.findAll(pageRequest);
        return enderecos.map(UsuarioPessoaDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public UsuarioPessoaDTO findById(Long id) {
        var endereco = repo.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Endereço não encontrado")
        );

        return UsuarioPessoaDTO.fromEntity(endereco);
    }

    @Transactional
    public UsuarioDTO save(UsuarioDTO dto) {
        var entity = UsuarioDTO.toEntity(dto);
        var enderecoSaved = repo.save(entity);
        return UsuarioDTO.fromEntity(enderecoSaved);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        try {
            Usuario entity = repo.getReferenceById(id);
            UsuarioDTO.mapperDtoToEntity(dto, entity);
            entity = repo.save(entity);
            return UsuarioDTO.fromEntity(entity);

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
