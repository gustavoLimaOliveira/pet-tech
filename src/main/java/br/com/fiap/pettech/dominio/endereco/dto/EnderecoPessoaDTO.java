package br.com.fiap.pettech.dominio.endereco.dto;

import br.com.fiap.pettech.dominio.endereco.entitie.Endereco;
import br.com.fiap.pettech.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoPessoaDTO(
        Long id,
        @NotBlank(message = "A rua não pode estar em branco")
        String rua,
        @NotBlank(message = "A cidade não pode estar em branco")
        String cidade,
        @NotBlank(message = "O estado não pode estar em branco")
        @Size(min = 2, max =2 , message = "O estado deve ter exatamente 2 caracteres")
        String estado,
        @NotBlank(message = "O CEP não pode estar em branco")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve ertar no formato 00000-000")
        String cep,

        PessoaDTO pessoa

) {
    public static Endereco toEntity(EnderecoPessoaDTO dto, Pessoa pessoa) {
        return new Endereco(dto, pessoa);
    }

    public static EnderecoPessoaDTO fromEntity(Endereco endereco) {
        return  new EnderecoPessoaDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getPessoa() != null ? PessoaDTO.fromEntity(endereco.getPessoa()) : null
        );
    }

    public static Endereco mapperDtoToEntity(
            EnderecoPessoaDTO dto,
            Endereco entity,
            Pessoa pessoa
    ) {
        entity.setRua(dto.rua());
        entity.setCidade(dto.cidade());
        entity.setEstado(dto.estado());
        entity.setCep(dto.cep());
        entity.setPessoa(pessoa);
        return entity;
    }


}
