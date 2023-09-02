package br.com.fiap.pettech.dominio.usuario.controller;

import br.com.fiap.pettech.dominio.usuario.dto.UsuarioDTO;
import br.com.fiap.pettech.dominio.usuario.dto.UsuarioPessoaDTO;
import br.com.fiap.pettech.dominio.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioPessoaDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        var usuarios = usuarioService.findAll(pageRequest);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioPessoaDTO> findById(@PathVariable Long id) {
        var usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO dto) {
        var usuario = usuarioService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((usuario.id())).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(
            @Valid @RequestBody UsuarioDTO dto,
            @PathVariable Long id) {
        var usuario = usuarioService.update(id, dto);
        return ResponseEntity.ok(usuario);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
