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

@ExtendWith(MockitoExtension.class)
public class CreateProductAdapterTest {

    @Mock
    private InMemoryProductRepository productRepository;

    @Mock
    private InMemoryInventoryRepository inventoryRepository;

    @InjectMocks
    private CreateProductAdapter createProductAdapter;


    @Test
    public void deveSalvarProdutoEInventory() {

        Long sku = 123L;
        ProductDetails productDetails = new ProductDetails("Nome do produto");
        List<Warehouse> warehouseList = Arrays.asList(Warehouse.builder().build());

        createProductAdapter.create(sku, productDetails, warehouseList);

        verify(productRepository).save(sku, productDetails);
        verify(inventoryRepository).save(sku, warehouseList);
    }
}
