package br.com.fiap.pettech.dominio.produto.entitie;

import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="tb_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
        private String nome;
        private String descricao;
        private String urlImagem;
        private double preco;

        @ManyToMany
                @JoinTable(
                        name = "tb_produto_categoria",
                        joinColumns = @JoinColumn(name = "produto_id"),
                        inverseJoinColumns = @JoinColumn(name = "categoria_id")
                )
        Set<Categoria> categorias = new HashSet<>();

    public Produto() {}

    public Produto(String nome, String descricao, String urlImagem, double preco) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
        this.preco = preco;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                ", preco=" + preco +
                '}';
    }
}
