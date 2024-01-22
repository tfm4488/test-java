package br.com.blz.testjava.core.usecase.impl;

import br.com.blz.testjava.adapters.controller.util.exception.NotFoundException;
import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.ports.FindInventoryPort;
import br.com.blz.testjava.core.ports.FindProductPort;
import br.com.blz.testjava.core.usecase.FindProductService;
import br.com.blz.testjava.core.usecase.dto.FindProductServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindProductServiceImpl implements FindProductService {

    @Autowired
    FindProductPort findProductPort;
    @Autowired
    FindInventoryPort findInventoryPort;
    @Override
    public FindProductServiceResponse execute(Long sku) {
        Optional<ProductDetails> productDetails = findProductPort.find(sku);

        if(!productDetails.isPresent()){
            throw new NotFoundException("Produto com o sku " + sku + " não encontrado");
        }

        Optional<List<Warehouse>> warehouses = findInventoryPort.find(sku);

        Long inventoryQuantity = 0L;

        if(warehouses.isPresent() && !warehouses.get().isEmpty() ) {
            inventoryQuantity = warehouses.get().stream()
                .mapToLong(Warehouse::getQuantity)
                .sum();
        }

        return FindProductServiceResponse.builder()
                .productDetails(productDetails.get())
                .warehouseList(warehouses.orElseGet(List::of))
                .inventoryQuantity(inventoryQuantity)
                .isMarketable(inventoryQuantity > 0L)
                .build();
    }
}
