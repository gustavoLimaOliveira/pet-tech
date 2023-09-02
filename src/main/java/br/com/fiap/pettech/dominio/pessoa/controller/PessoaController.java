package br.com.fiap.pettech.dominio.pessoa.controller;

import br.com.fiap.pettech.dominio.pessoa.dto.PessoaEnderecoUsuarioDTO;
import br.com.fiap.pettech.dominio.pessoa.dto.PessoaUsuarioDTO;
import br.com.fiap.pettech.dominio.pessoa.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<Page<PessoaEnderecoUsuarioDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        var pessoas = pessoaService.findAll(pageRequest);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaEnderecoUsuarioDTO> findById(@PathVariable Long id) {
        var pessoa = pessoaService.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<PessoaUsuarioDTO> save(@Valid @RequestBody PessoaUsuarioDTO dto) {
        var pessoa = pessoaService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((pessoa.id())).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaUsuarioDTO> update(
            @Valid @RequestBody PessoaUsuarioDTO dto,
            @PathVariable Long id) {
        var pessoa = pessoaService.update(id, dto);
        return ResponseEntity.ok(pessoa);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
