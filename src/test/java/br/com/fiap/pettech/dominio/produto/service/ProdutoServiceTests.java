package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.exception.service.ControllerNotFoundException;
import br.com.fiap.pettech.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private IProdutoRepository repository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private PageImpl<Produto> page;
    private ProdutoDTO produtoDTO;
    private Produto produto;
    private String nomeAtualizado;

   @BeforeEach
    public void setUp() {
        idExistente = UUID.fromString("fdfe49fe-5be4-46ff-8fbe-e35b27b257e1");
        idNaoExistente = UUID.fromString("fdfe49fe-5be4-46ff-8fbe-e35b27b25750");
        pageRequest = PageRequest.of(0, 10);
        produto = Factory.createProduto();
        produtoDTO = Factory.createProdutoDTO();
        page = new PageImpl<>(List.of(produto));
        nomeAtualizado = "Produto Atualizado";

       Mockito.when(repository.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(produto));
       Mockito.when(repository.findAll((PageRequest) ArgumentMatchers.any())).thenReturn(page);
       Mockito.when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
    }



    @Test
    public void findAllDeveRetornarUmaListaDeProdutosDTO() {
        Page produtoDTO = service.findAll(this.pageRequest);
        Assertions.assertNotNull(produtoDTO);
    }
    @Test
    public void findByIdDeveRetornarUmProdutoDTOAoBuscarPorId() {
        ProdutoDTO produtoDTO = service.findById(this.idExistente);
        Assertions.assertNotNull(produtoDTO);
    }

    @Test
    public void findByIdDeveRetornarUmaExcesaoAoBuscarPorIdQueNaoExiste() {
       Assertions.assertThrows(ControllerNotFoundException.class, ()  -> {
           service.findById(this.idNaoExistente);
       });
    }

}
