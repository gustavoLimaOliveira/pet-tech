package br.com.fiap.pettech.dominio.usuario.dto;

import br.com.fiap.pettech.dominio.usuario.entitie.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        Long id,
        @NotBlank(message = "O nome de usuário não pode estar em branco")
        String username,
        @NotBlank(message = "O password não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter pelo menos {min} caracteres")
        String password
) {

    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getPassword());
    }

    public  static  UsuarioDTO fromEntity(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword()
        );
    }

    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        return  new Usuario(
                usuarioDTO.username,
                usuarioDTO.password
        );
    }

    public static  Usuario mapperDtoToEntity(
            UsuarioDTO usuarioDTO,
            Usuario usuario
    ) {
        usuario.setUsername(usuarioDTO.username);
        usuario.setPassword(usuarioDTO.password);
        return  usuario;
    }
}
