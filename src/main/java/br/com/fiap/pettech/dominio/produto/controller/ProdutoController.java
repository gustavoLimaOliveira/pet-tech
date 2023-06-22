package br.com.fiap.pettech.dominio.produto.controller;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.service.ProdutoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var produtos = produtoService.findAll(pageRequest);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable UUID id) {
        var produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO produto) {
        var produtoSaved = produtoService.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((produtoSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(produtoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@RequestBody ProdutoDTO produto, @PathVariable UUID id) {
        var produtoUpdated = produtoService.update(id, produto);
        return  ResponseEntity.ok(produtoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
