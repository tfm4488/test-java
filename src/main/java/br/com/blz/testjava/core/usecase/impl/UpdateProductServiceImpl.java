package br.com.blz.testjava.core.usecase.impl;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.ports.FindProductPort;
import br.com.blz.testjava.core.ports.UpdateProductPort;
import br.com.blz.testjava.core.usecase.UpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateProductServiceImpl implements UpdateProductService {

    @Autowired
    FindProductPort findProductPort;

    @Autowired
    UpdateProductPort updateProductPort;
    @Override
    public void execute(Long sku, ProductDetails productDetails, List<Warehouse> warehouseList) {

        if (!findProductPort.find(sku).isPresent()){
            throw new RuntimeException("O Produto com o sku " + sku + " não existe");
        }

        updateProductPort.execute(sku, productDetails, warehouseList);
    }
}
