package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.core.domain.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

    public class InMemoryInventoryRepositoryTest {

        private InMemoryInventoryRepository repository;
        private final Long testSku = 123L;
        private List<Warehouse> testWarehouses;

        @BeforeEach
        public void setUp() {
            repository = new InMemoryInventoryRepository();
            testWarehouses = Arrays.asList(new Warehouse("Local1", 10L, "Type1"),
                new Warehouse("Local2", 20L, "Type2"));
        }

        @Test
        public void salvarEPesquisarPorSKU() {
            repository.save(testSku, testWarehouses);
            Optional<List<Warehouse>> result = repository.findBySku(testSku);
            Assertions.assertTrue(result.isPresent());
            Assertions.assertEquals(testWarehouses, result.get());
        }

        @Test
        public void SKUNaoEncontrado() {
            Optional<List<Warehouse>> result = repository.findBySku(123L);
            Assertions.assertFalse(result.isPresent());
        }

        @Test
        public void delecaoInventoryPorSKU() {
            repository.save(testSku, testWarehouses);
            repository.deleteBySku(testSku);
            Optional<List<Warehouse>> result = repository.findBySku(testSku);
            Assertions.assertFalse(result.isPresent());
        }

        @Test
        public void updateInventoryPorSKU() {
            repository.save(testSku, testWarehouses);
            List<Warehouse> updatedWarehouses = Arrays.asList(new Warehouse("Local3", 30L, "Type3"));
            repository.update(testSku, updatedWarehouses);
            Optional<List<Warehouse>> result = repository.findBySku(testSku);
            Assertions.assertTrue(result.isPresent());
            Assertions.assertEquals(updatedWarehouses, result.get());
        }

        @Test
        public void updateInventoryInexistente() {
            Assertions.assertThrows(NoSuchElementException.class, () -> {
                repository.update(123L, testWarehouses);
            });
        }
    }


