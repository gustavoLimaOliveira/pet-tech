package br.com.fiap.pettech.dominio.pessoa.dto;


import br.com.fiap.pettech.dominio.endereco.dto.EnderecoDTO;
import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.dominio.usuario.dto.UsuarioDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public record PessoaEnderecoUsuarioDTO(
        Long id,
        @NotBlank(message = "O CPF não pode estar em branco")
        @CPF(message = "CPF inválido")
        String cpf,
        String nome,
        LocalDate nascimento,
        @Email(message = "O email deve estar em um formato válido")
        String email,

        Set<EnderecoDTO> enderecos,
        UsuarioDTO usuario
) {

    public static PessoaEnderecoUsuarioDTO fromEntity(Pessoa pessoa) {
        Set<EnderecoDTO> enderecos = new HashSet<>();

        if(!pessoa.getEnderecos().isEmpty()) {
            pessoa.getEnderecos().forEach( endereco -> {
                enderecos.add(EnderecoDTO.fromEntity(endereco));
            });
        }

        return  new PessoaEnderecoUsuarioDTO(
                pessoa.getId(),
                pessoa.getCpf(),
                pessoa.getNome(),
                pessoa.getNascimento(),
                pessoa.getEmail(),
                enderecos,
                pessoa.getUsuario() != null ? new UsuarioDTO(pessoa.getUsuario()) : null
        );
    }

}
