package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.core.domain.ProductDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.NoSuchElementException;
import java.util.Optional;



class InMemoryProductRepositoryTest {
    private InMemoryProductRepository repository;
    private final Long testSku = 123L;
    private ProductDetails testProductDetails;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryProductRepository();
        testProductDetails = new ProductDetails("Nome do produto");
    }

    @Test
    public void salvarEPesquisarPorSKU() {
        repository.save(testSku, testProductDetails);
        Optional<ProductDetails> result = repository.findBySku(testSku);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(testProductDetails, result.get());
    }

    @Test
    public void SKUNaoEncontrado() {
        Optional<ProductDetails> result = repository.findBySku(555L);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void delecaoPorSKU() {
        repository.save(testSku, testProductDetails);
        repository.deleteBySku(testSku);
        Optional<ProductDetails> result = repository.findBySku(testSku);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void updatePorSKU() {
        repository.save(testSku, testProductDetails);
        ProductDetails updatedProductDetails = new ProductDetails("Novo nome do produto");
        repository.update(testSku, updatedProductDetails);
        Optional<ProductDetails> result = repository.findBySku(testSku);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(updatedProductDetails, result.get());
    }

    @Test
    public void updateEmProdutoInexistente() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            repository.update(999L, testProductDetails);
        });
    }
}
