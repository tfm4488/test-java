package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.core.ports.DeleteProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductAdapter implements DeleteProductPort {
    @Autowired
    private InMemoryProductRepository inMemoryProductRepository;

    @Autowired
    private InMemoryInventoryRepository inMemoryInventoryRepository;


    @Override
    public void execute(Long sku) {
        inMemoryInventoryRepository.deleteBySku(sku);
        inMemoryProductRepository.deleteBySku(sku);

    }
}
