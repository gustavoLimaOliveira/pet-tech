package br.com.fiap.pettech.dominio.pessoa.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class PessoaFisica extends Pessoa {

    private String cpf;

    private Collection<PessoaFisica> dependentes = new Vector<>();

    public PessoaFisica() {
    }

    public PessoaFisica(Long id, String nome, LocalDate nascimento, String cpf, Collection<PessoaFisica> dependentes) {
        super(id, nome, nascimento);
        this.cpf = cpf;
        this.dependentes = dependentes;
    }

    public String getCpf() {
        return cpf;
    }

    public PessoaFisica setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public PessoaFisica addDependente(PessoaFisica p) {
        //TODO: Não pode ser a mesma pessoa.
        dependentes.add(p);
        return this;
    }

    public PessoaFisica removeDependente(PessoaFisica p) {
        dependentes.remove(p);
        return this;
    }

    public Collection<PessoaFisica> getDependentes() {
        return Collections.unmodifiableCollection(dependentes);
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "cpf='" + cpf + '\'' +
                ", dependentes=" + dependentes +
                "} " + super.toString();
    }
}
