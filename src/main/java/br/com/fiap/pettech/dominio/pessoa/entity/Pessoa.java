package br.com.fiap.pettech.dominio.pessoa.entity;

import br.com.fiap.pettech.dominio.endereco.entitie.Endereco;
import br.com.fiap.pettech.dominio.pessoa.dto.PessoaDTO;
import br.com.fiap.pettech.dominio.pessoa.dto.PessoaUsuarioDTO;
import br.com.fiap.pettech.dominio.usuario.entitie.Usuario;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "tb_pessoa")
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate nascimento;

    private String cpf;

    private String email;

    @OneToMany(mappedBy = "pessoa")
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, LocalDate nascimento, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.email = email;
    }

    public Pessoa(PessoaDTO dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.nascimento = dto.nascimento();
        this.cpf = dto.cpf();
        this.email = dto.email();
    }

    public Pessoa(PessoaUsuarioDTO dto, Usuario usuario) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.nascimento = dto.nascimento();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public Pessoa setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Pessoa setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;

        return getId().equals(pessoa.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                '}';
    }
}
