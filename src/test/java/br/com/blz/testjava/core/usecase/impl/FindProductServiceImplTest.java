package br.com.blz.testjava.core.usecase.impl;

import br.com.blz.testjava.adapters.controller.util.exception.NotFoundException;
import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.ports.FindInventoryPort;
import br.com.blz.testjava.core.ports.FindProductPort;
import br.com.blz.testjava.core.usecase.dto.FindProductServiceResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FindProductServiceImplTest {

    @Mock
    private FindProductPort findProductPort;

    @Mock
    private FindInventoryPort findInventoryPort;

    @InjectMocks
    private FindProductServiceImpl findProductService;

    @Test
    public void deveRetornarPayloadComTodosOsDadosDoProduto() {
        Long sku = 123L;
        ProductDetails productDetails = new ProductDetails("Nome do Produto");
        List<Warehouse> warehouseList = List.of(
            Warehouse.builder()
                .locality("localityTeste")
                .quantity(1L)
                .type("typeTeste").build(),
            Warehouse.builder()
                .locality("localityTeste2")
                .quantity(2L)
                .type("typeTeste2").build());
        Long expectedInventoryQuantity = warehouseList.stream().mapToLong(Warehouse::getQuantity).sum();

        when(findProductPort.find(sku)).thenReturn(Optional.of(productDetails));
        when(findInventoryPort.find(sku)).thenReturn(Optional.of(warehouseList));

        FindProductServiceResponse response = findProductService.execute(sku);

        assertNotNull(response);
        assertEquals(productDetails, response.getProductDetails());
        assertEquals(warehouseList, response.getWarehouseList());
        assertEquals(expectedInventoryQuantity, response.getInventoryQuantity());
        assertEquals(expectedInventoryQuantity > 0L, response.getIsMarketable());
    }

    @Test
    public void deveLancarExceptionProdutoNaoEncontrado() {
        Long sku = 123L;
        when(findProductPort.find(sku)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> findProductService.execute(sku));
    }


    @Test
    public void deveRetornarProdutoMesmoSemWarehousesCadastrada() {
        Long sku = 123L;
        ProductDetails productDetails = new ProductDetails("Nome do Produto");
        List<Warehouse> emptyWarehouseList = Arrays.asList();

        when(findProductPort.find(sku)).thenReturn(Optional.of(productDetails));
        when(findInventoryPort.find(sku)).thenReturn(Optional.of(emptyWarehouseList));

        FindProductServiceResponse response = findProductService.execute(sku);

        assertNotNull(response);
        assertEquals(productDetails, response.getProductDetails());
        assertTrue(response.getWarehouseList().isEmpty());
        assertEquals(0L, response.getInventoryQuantity());
        assertFalse(response.getIsMarketable());
    }

    @Test
    public void deveRetornarProdutoMesmoComInventoryVazio() {
        Long sku = 123L;
        ProductDetails productDetails = new ProductDetails("Nome do Produto");

        when(findProductPort.find(sku)).thenReturn(Optional.of(productDetails));
        when(findInventoryPort.find(sku)).thenReturn(Optional.empty());

        FindProductServiceResponse response = findProductService.execute(sku);

        assertNotNull(response);
        assertEquals(productDetails, response.getProductDetails());
        assertEquals(response.getWarehouseList(), List.of());
        assertEquals(0L, response.getInventoryQuantity());
        assertFalse(response.getIsMarketable());
    }

}
