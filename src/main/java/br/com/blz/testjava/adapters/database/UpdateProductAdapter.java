package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.ports.UpdateProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateProductAdapter implements UpdateProductPort {

    @Autowired
    private InMemoryProductRepository inMemoryProductRepository;

    @Autowired
    private InMemoryInventoryRepository inMemoryInventoryRepository;

    @Override
    public void execute(Long sku, ProductDetails productDetails, List<Warehouse> warehouseList) {
        if (productDetails != null){
            inMemoryProductRepository.update(sku,productDetails);
        }

        if (warehouseList !=null && !warehouseList.isEmpty()){
            inMemoryInventoryRepository.update(sku,warehouseList);
        }

    }
}
