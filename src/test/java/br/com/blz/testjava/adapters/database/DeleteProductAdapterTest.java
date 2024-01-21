package br.com.blz.testjava.adapters.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteProductAdapterTest {

    @Mock
    private InMemoryProductRepository productRepository;

    @Mock
    private InMemoryInventoryRepository inventoryRepository;

    @InjectMocks
    private DeleteProductAdapter deleteProductAdapter;


    @Test
    public void deveDeletarProdutoEInventory() {
        Long sku = 123L;

        deleteProductAdapter.execute(sku);

        verify(inventoryRepository).deleteBySku(sku);
        verify(productRepository).deleteBySku(sku);
    }
}
