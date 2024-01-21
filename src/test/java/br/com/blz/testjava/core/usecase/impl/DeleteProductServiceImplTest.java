package br.com.blz.testjava.core.usecase.impl;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.ports.DeleteProductPort;
import br.com.blz.testjava.core.ports.FindProductPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleteProductServiceImplTest {

    @Mock
    private FindProductPort findProductPort;

    @Mock
    private DeleteProductPort deleteProductPort;

    @InjectMocks
    private DeleteProductServiceImpl deleteProductService;

    @Test
    public void pesquisaPorProdutoInexistenteDeveLancarException() {
        Long sku = 123L;
        when(findProductPort.find(sku)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            deleteProductService.execute(sku);
        });

        assertTrue(thrown.getMessage().contains("O Produto com o sku " + sku + " não existe"));
        verify(deleteProductPort, never()).execute(sku);
    }

    @Test
    public void produtoDeveSerDeletado() {
        Long sku = 123L;
        when(findProductPort.find(sku)).thenReturn(Optional.of(new ProductDetails("Nome do Produto")));

        deleteProductService.execute(sku);

        verify(deleteProductPort).execute(sku);
    }
}
