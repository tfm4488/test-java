package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class UpdateProductAdapterTest {

    @Mock
    private InMemoryProductRepository productRepository;

    @Mock
    private InMemoryInventoryRepository inventoryRepository;

    @InjectMocks
    private UpdateProductAdapter updateProductAdapter;


    @Test
    public void deveAtualizarProdutoEInventory() {
        Long sku = 123L;
        ProductDetails productDetails = new ProductDetails("Novo Nome do Produto");
        List<Warehouse> warehouseList = List.of(
            Warehouse.builder()
                .locality("localityTeste")
                .quantity(1L)
                .type("typeTeste").build(),
            Warehouse.builder()
                .locality("localityTeste2")
                .quantity(2L)
                .type("typeTeste2").build());

        updateProductAdapter.execute(sku, productDetails, warehouseList);

        verify(productRepository).update(sku, productDetails);
        verify(inventoryRepository).update(sku, warehouseList);
    }

    @Test
    public void deveAtualizarSomenteProduto() {
        Long sku = 123L;
        ProductDetails productDetails = new ProductDetails("Novo Nome do Produto");
        List<Warehouse> warehouseList = Arrays.asList();

        updateProductAdapter.execute(sku, productDetails, warehouseList);

        verify(productRepository).update(sku, productDetails);
        verify(inventoryRepository, never()).update(sku, warehouseList);
    }

    @Test
    public void deveAtualizarSomenteInventory() {
        Long sku = 123L;
        List<Warehouse> warehouseList = List.of(
            Warehouse.builder()
                .locality("localityTeste")
                .quantity(5L)
                .type("typeTeste").build(),
            Warehouse.builder()
                .locality("localityTeste2")
                .quantity(3L)
                .type("typeTeste2").build());;

        updateProductAdapter.execute(sku, null, warehouseList);

        verify(productRepository, never()).update(sku, null);
        verify(inventoryRepository).update(sku, warehouseList);
    }
}
