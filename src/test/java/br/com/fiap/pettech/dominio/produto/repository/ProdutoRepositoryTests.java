package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTests {
    @Autowired
    private IProdutoRepository produtoRepository;

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        UUID id = UUID.fromString("fdfe49fe-5be4-46ff-8fbe-e35b27b257e1");
        Optional<Produto> result = produtoRepository.findById(id);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExcelptionCasoIdNaoExista() {
        UUID id = UUID.fromString("fdfe49fe-5be4-46ff-8fbe-e35b27b25750");

        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        });
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {
        Produto produto = new Produto();
        produto.setNome("PC");
        produto.setPreco(3000.65);
        produto.setDescricao("Pc Gammer");
        produto.setUrlImagem("url 1");
        produto.setId(null);

        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo.getId());
    }
}
