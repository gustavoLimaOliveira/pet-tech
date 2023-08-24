package br.com.fiap.pettech.dominio.usuario.dto;

import br.com.fiap.pettech.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.pettech.dominio.usuario.entitie.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioPessoaDTO(
        Long id,
        @NotBlank(message = "O nome de usuário não pode estar em branco")
        String username,
        @NotBlank(message = "O password não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter pelo menos {min} caracteres")
        String password,
        PessoaDTO pessoa
) {
    public  static UsuarioPessoaDTO fromEntity(Usuario usuario) {
        return new UsuarioPessoaDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getPessoa() != null ? new PessoaDTO(usuario.getPessoa()) : null
        );
    }


}
