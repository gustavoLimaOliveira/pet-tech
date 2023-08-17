package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.exception.service.ControllerNotFoundException;
import br.com.fiap.pettech.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTests {
    @Autowired
    private IProdutoRepository produtoRepository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private long countTotalProdutos;
    private String nomeAtualizado;

    @BeforeEach
    void setuUp() throws  Exception {
        idExistente = UUID.fromString("fdfe49fe-5be4-46ff-8fbe-e35b27b257e1");
        idNaoExistente = UUID.fromString("fdfe49fe-5be4-46ff-8fbe-e35b27b25750");
        pageRequest = PageRequest.of(10, 10);
        countTotalProdutos = 5L;
        nomeAtualizado = "Atualização nome do produto";
    }

    @Test
    public void findAllDeveRetornarListaDeObjetosCadastrados() {
        Page produtos = produtoRepository.findAll(this.pageRequest);
        Assertions.assertEquals(produtos.getTotalElements(), countTotalProdutos);
    }

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        Optional<Produto> result = produtoRepository.findById(this.idExistente);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExcelptionCasoIdNaoExista() {
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(this.idNaoExistente).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        });
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {
        Produto produto = Factory.createProduto();
        produto.setId(null);
        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo.getId());
    }

    @Test
    public void saveDeveAtualizarObjetoCasoIdNaoSejaNull() {
        Produto produto = Factory.createProduto();
        produto.setId(this.idExistente);
        produto.setNome(this.nomeAtualizado);

        var produtoSalvo = produtoRepository.save(produto);

        Assertions.assertEquals(produtoSalvo.getNome(), this.nomeAtualizado);
    }

    @Test
    public void deleteDeveDeletarObjetoCasoExista() {
        produtoRepository.deleteById(this.idExistente);
        Optional<Produto> result = produtoRepository.findById(this.idExistente);
        Assertions.assertFalse(result.isPresent());
    }
}
