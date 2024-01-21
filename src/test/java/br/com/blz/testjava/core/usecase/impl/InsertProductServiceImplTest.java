package br.com.blz.testjava.core.usecase.impl;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.ports.CreateProductPort;
import br.com.blz.testjava.core.ports.FindProductPort;
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
public class InsertProductServiceImplTest {

    @Mock
    private FindProductPort findProductPort;

    @Mock
    private CreateProductPort createProductPort;

    @InjectMocks
    private InsertProductServiceImpl insertProductService;

    @Test
    public void deveLancarExceptionAoCadastrarProdutoJaExistente() {
        Long sku = 123L;
        when(findProductPort.find(sku)).thenReturn(Optional.of(new ProductDetails("Nome do Produto")));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            insertProductService.execute(sku, new ProductDetails("Nome do Produto"), List.of(
                Warehouse.builder()
                    .locality("localityTeste")
                    .quantity(1L)
                    .type("typeTeste").build(),
                Warehouse.builder()
                    .locality("localityTeste2")
                    .quantity(2L)
                    .type("typeTeste2").build()));
        });

        assertTrue(thrown.getMessage().contains("Produto já existente na base com sku " + sku));
        verify(createProductPort, never()).create(sku, new ProductDetails("Nome do Produto"), List.of(
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
    public void deveCriarNovoProduto() {
        Long sku = 123L;
        when(findProductPort.find(sku)).thenReturn(Optional.empty());

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

        insertProductService.execute(sku, productDetails, warehouseList);

        verify(createProductPort).create(sku, productDetails, warehouseList);
    }
}
