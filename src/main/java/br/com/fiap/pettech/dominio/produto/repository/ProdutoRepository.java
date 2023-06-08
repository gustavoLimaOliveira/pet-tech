package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class ProdutoRepository implements IProdutoRepository {

    private static  Set<Produto> produtos;

    static {
        produtos = new LinkedHashSet<>();

        Produto produto1 = new Produto("produto 1 ", "Descricao 1", "URL imagem", 19.99);
        Produto produto2 = new Produto("produto 2 ", "Descricao 2", "URL imagem 2", 20.99);

        produtos.add(produto1);
        produtos.add(produto2);

    }

    @Override
    public Optional<Produto> findById(UUID id) {
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Set<Produto> findAll() {
        return produtos;
    }

    @Override
    public Produto save(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    @Override
    public Produto update(UUID id, Produto produto) {
        var  produtoBuscado = produtos.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        produtoBuscado.setNome(produto.getNome());
        produtoBuscado.setDescricao(produto.getDescricao());
        produtoBuscado.setPreco(produto.getPreco());
        produtoBuscado.setUrlImagem(produto.getUrlImagem());
        return produtoBuscado;
    }

    @Override
    public void delete(UUID id) {
        produtos.removeIf(p -> p.getId().equals(id));
    }
}
