package br.com.fiap.pettech.dominio.pessoa.repository;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;

import java.util.List;

public interface IPessoaRepository {
    List<Pessoa> findAll(int page, int pageSize);
    Pessoa findById(Long id);
    Pessoa save(Pessoa pessoa);
    Pessoa update(Long id, Pessoa pessoa);
    void deleteById(Long id);
}
