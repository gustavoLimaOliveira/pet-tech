package br.com.fiap.pettech.dominio.pessoa.controller;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.dominio.pessoa.repository.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final IPessoaRepository repo;

    @Autowired
    public PessoaController(IPessoaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll(
            @RequestParam(value = "pagina", defaultValue = "1") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ) {
        var pessoas = repo.findAll(pagina, tamanho);
        return  ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        var pessoa = repo.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
        var pessoaSaved = repo.save(pessoa);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(pessoaSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa, @PathVariable Long id) {
        var pessoaUpdated = repo.update(id, pessoa);
        return ResponseEntity.ok(pessoaUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
