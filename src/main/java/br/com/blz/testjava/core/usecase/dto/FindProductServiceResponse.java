package br.com.blz.testjava.core.usecase.dto;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FindProductServiceResponse {

    ProductDetails productDetails;

    List<Warehouse> warehouseList;

    Long inventoryQuantity;

    Boolean isMarketable;
}
