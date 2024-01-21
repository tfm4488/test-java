package br.com.blz.testjava.core.usecase.impl;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.ports.CreateProductPort;
import br.com.blz.testjava.core.ports.FindProductPort;
import br.com.blz.testjava.core.usecase.InsertProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsertProductServiceImpl implements InsertProductService {

    @Autowired
    FindProductPort findProductPort;
    @Autowired
    CreateProductPort createProductPort;
    @Override
    public void execute(Long sku, ProductDetails productDetails, List<Warehouse> warehouseList) {

        if(findProductPort.find(sku).isPresent()){
            throw new RuntimeException("Produto já existente na base com sku " + sku);
        }

        createProductPort.create(sku,productDetails,warehouseList);
    }
}
