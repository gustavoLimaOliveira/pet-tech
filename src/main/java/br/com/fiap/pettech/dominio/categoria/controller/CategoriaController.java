package br.com.fiap.pettech.dominio.categoria.controller;


import br.com.fiap.pettech.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.pettech.dominio.categoria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "quatidade", defaultValue = "10") Integer quantidade,
            @RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, quantidade, Sort.Direction.valueOf(direcao), ordenacao);
        var list = service.findAll(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(
        @PathVariable Long id
    ) {
        CategoriaDTO categoriaDTO = service.findById(id);
        return ResponseEntity.ok(categoriaDTO);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> insert(
      @RequestBody CategoriaDTO dto
    ){
        CategoriaDTO categoriaDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((categoriaDto.getId())).toUri();
        return ResponseEntity.created(uri).body(categoriaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(
        @PathVariable Long id,
        @RequestBody CategoriaDTO dto
    ) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
        @PathVariable Long id
    ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
