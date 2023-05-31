package br.com.fiap.pettech.dominio.pessoa.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class PessoaJuridica extends Pessoa {

    private String cnpj;

    private Collection<Pessoa> socios = new Vector<>();


    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id, String nome, LocalDate nascimento, String cnpj, Collection<Pessoa> socios) {
        super(id, nome, nascimento);
        this.cnpj = cnpj;
        this.socios = socios;
    }

    public PessoaJuridica addSocio(Pessoa p) {
        //TODO: não pode ser a mesma pessoa
        socios.add(p);
        return this;
    }

    public PessoaJuridica removerSocio(Pessoa p) {
        socios.remove(p);
        return this;
    }

    public Collection<Pessoa> getSocios() {
        return Collections.unmodifiableCollection(socios);
    }

    public String getCnpj() {
        return cnpj;
    }

    public PessoaJuridica setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }


    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "cnpj='" + cnpj + '\'' +
                ", socios=" + socios +
                "} " + super.toString();
    }
}
