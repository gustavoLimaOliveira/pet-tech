package br.com.fiap.pettech.dominio.categoria.entity;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataDeCriacao;

    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos = new HashSet<>();

    public Categoria() {}

    public Categoria(Long id, String nome, Instant dataDeCriacao) {
        this.id = id;
        this.nome = nome;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instant getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Instant dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    @PrePersist
    public void prePersist() {
        dataDeCriacao = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
