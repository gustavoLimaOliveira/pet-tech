package br.com.fiap.pettech.testes;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;

public class Factory {

    public static Produto createProduto() {
        Produto produto = new Produto("Iphone", "Descrição 1", "Url 1", 800.0);
        return  produto;
    }

    public static ProdutoDTO createProdutoDTO() {
        Produto produto = createProduto();
        return new ProdutoDTO(produto);
    }
}
