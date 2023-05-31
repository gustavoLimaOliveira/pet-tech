package br.com.fiap.pettech.dominio.pessoa.repository;

import br.com.fiap.pettech.dominio.pessoa.entity.PessoaFisica;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class PessoaFisicaCollectionRepository {

    static private Set<PessoaFisica> pessoas;

    static {

        pessoas = new LinkedHashSet<>();

        PessoaFisica p1 = new PessoaFisica();
        p1.setCpf("132132132131").setNome("Benefrancis do Nascimento").setNascimento(LocalDate.of(1977, 3, 8));

        PessoaFisica dep1 = new PessoaFisica();
        dep1.setCpf("132154354").setNome("Bruno Sudré do Nascimento").setNascimento(LocalDate.of(2000, 5, 15));

        p1.addDependente(dep1);

        save(p1);

        save(dep1);
    }

    public Collection<PessoaFisica> findAll() {
        return pessoas;
    }

    public Optional<PessoaFisica> findById(Long id) {
        return pessoas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public static PessoaFisica save(PessoaFisica p) {
        p.setId(pessoas.size() + 1L);
        pessoas.add(p);
        return p;
    }


}
