package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.ports.CreateProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateProductAdapter implements CreateProductPort {

    @Autowired
    private InMemoryProductRepository inMemoryProductRepository;

    @Autowired
    private InMemoryInventoryRepository inMemoryInventoryRepository;
    @Override
    public void create(Long sku, ProductDetails productDetails, List<Warehouse> warehouseList) {
        inMemoryProductRepository.save(sku, productDetails);
        inMemoryInventoryRepository.save(sku, warehouseList);

    }
}
