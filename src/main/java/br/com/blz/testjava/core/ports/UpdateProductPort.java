package br.com.blz.testjava.core.ports;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import java.util.List;

public interface UpdateProductPort {

    void execute (Long sku, ProductDetails productDetails, List <Warehouse> warehouseList);
}
