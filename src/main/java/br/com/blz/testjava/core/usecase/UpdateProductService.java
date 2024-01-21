package br.com.blz.testjava.core.usecase;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;

import java.util.List;

public interface UpdateProductService {

    void execute (Long sku, ProductDetails productDetails, List <Warehouse> warehouseList);
}
