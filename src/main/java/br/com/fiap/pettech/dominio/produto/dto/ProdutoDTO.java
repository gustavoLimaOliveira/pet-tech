package br.com.fiap.pettech.dominio.produto.dto;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;

import java.util.UUID;

public class ProdutoDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private String urlImagem;
    private double preco;

    public ProdutoDTO(){}

    public ProdutoDTO(UUID id, String nome, String descricao, String urlImagem, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
        this.preco = preco;
    }

    public ProdutoDTO(Produto entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.descricao = entidade.getDescricao();
        this.urlImagem = entidade.getUrlImagem();
        this.preco = entidade.getPreco();
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
}
