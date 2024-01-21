package br.com.blz.testjava.core.usecase.impl;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.ports.FindProductPort;
import br.com.blz.testjava.core.ports.UpdateProductPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UpdateProductServiceImplTest {

    @Mock
    private FindProductPort findProductPort;

    @Mock
    private UpdateProductPort updateProductPort;

    @InjectMocks
    private UpdateProductServiceImpl updateProductService;

    @Test
    public void deveLancarExceptionAoAtualizarProdutoInexistente() {
        Long sku = 123L;
        when(findProductPort.find(sku)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            updateProductService.execute(sku, new ProductDetails("Nome do Produto"), List.of(
                Warehouse.builder()
                    .locality("localityTeste")
                    .quantity(1L)
                    .type("typeTeste").build(),
                Warehouse.builder()
                    .locality("localityTeste2")
                    .quantity(2L)
                    .type("typeTeste2").build()));
        });

        assertTrue(thrown.getMessage().contains("O Produto com o sku " + sku + " não existe"));
        verify(updateProductPort, never()).execute(sku, new ProductDetails("Nome do Produto"), List.of(
            Warehouse.builder()
                .locality("localityTeste")
                .quantity(1L)
                .type("typeTeste").build(),
            Warehouse.builder()
                .locality("localityTeste2")
                .quantity(2L)
                .type("typeTeste2").build()));
    }


    @Test
    public void deveAtualizarProdutoExistente() {
        Long sku = 123L;
        when(findProductPort.find(sku)).thenReturn(Optional.of(new ProductDetails("Nome do Produto")));

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

        updateProductService.execute(sku, productDetails, warehouseList);

        verify(updateProductPort).execute(sku, productDetails, warehouseList);
    }
}
