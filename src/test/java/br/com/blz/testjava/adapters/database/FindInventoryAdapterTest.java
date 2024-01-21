package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.adapters.database.FindInventoryAdapter;
import br.com.blz.testjava.core.domain.Warehouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FindInventoryAdapterTest {

    @Mock
    private InMemoryInventoryRepository inventoryRepository;

    @InjectMocks
    private FindInventoryAdapter findInventoryAdapter;

    @Test
    public void deveRetornarInventory() {
        Long sku = 123L;
        List<Warehouse> expectedWarehouseList = List.of(
            Warehouse.builder()
                .locality("localityTeste")
                .quantity(1L)
                .type("typeTeste").build(),
            Warehouse.builder()
                .locality("localityTeste2")
                .quantity(2L)
                .type("typeTeste2").build());
        Optional<List<Warehouse>> expectedResponse = Optional.of(expectedWarehouseList);


        when(inventoryRepository.findBySku(sku)).thenReturn(expectedResponse);


        Optional<List<Warehouse>> actualResponse = findInventoryAdapter.find(sku);


        verify(inventoryRepository).findBySku(sku);
        assertEquals(expectedResponse, actualResponse);
    }
}
