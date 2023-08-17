package br.com.fiap.pettech.dominio.endereco.dto;

import br.com.fiap.pettech.dominio.endereco.entitie.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
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
        String cep


) {
    public static Endereco toEntity(EnderecoDTO dto) {
        return new Endereco(dto);
    }

    public static EnderecoDTO fromEntity(Endereco endereco) {
        return  new EnderecoDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }

    public static Endereco mapperDtoToEntity(
            EnderecoDTO dto,
            Endereco entity) {
        entity.setRua(dto.rua());
        entity.setCidade(dto.cidade());
        entity.setEstado(dto.estado());
        entity.setCep(dto.cep());
        return entity;
    }


}
