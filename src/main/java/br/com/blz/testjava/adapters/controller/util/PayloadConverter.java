package br.com.blz.testjava.adapters.controller.util;

import br.com.blz.testjava.adapters.controller.dto.request.CreateProductRequest;
import br.com.blz.testjava.adapters.controller.dto.request.UpdateProductRequest;
import br.com.blz.testjava.adapters.controller.dto.response.FindProductResponse;
import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.usecase.dto.FindProductServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PayloadConverter{

    public ProductDetails toProductDetailsDomain(CreateProductRequest createProductRequest){

        return ProductDetails.builder()
            .name(createProductRequest.getName())
            .build();
    }

    public List<Warehouse> toWarehouseDomainList(CreateProductRequest createProductRequest){

        return createProductRequest.getWarehouses()
            .stream()
            .map(inputWarehouse -> Warehouse.builder()
                .locality(inputWarehouse.getLocality())
                .quantity(inputWarehouse.getQuantity())
                .type(inputWarehouse.getType())
                .build())
            .collect(Collectors.toList());
    }

    public ProductDetails toProductDetailsDomain(UpdateProductRequest updateProductRequest){

        if(updateProductRequest.getName() == null) {
            return null;
        }

        return ProductDetails.builder()
            .name(updateProductRequest.getName())
            .build();
    }

    public List<Warehouse> toWarehouseDomainList(UpdateProductRequest updateProductRequest){

        if(updateProductRequest.getWarehouses() == null || updateProductRequest.getWarehouses().isEmpty()) {
            return null;
        }

        return updateProductRequest.getWarehouses()
            .stream()
            .map(inputWarehouse -> Warehouse.builder()
                .locality(inputWarehouse.getLocality())
                .quantity(inputWarehouse.getQuantity())
                .type(inputWarehouse.getType())
                .build())
            .collect(Collectors.toList());
    }

    public FindProductResponse toFindProductResponse(Long sku, FindProductServiceResponse findProductServiceResponse){

       return  FindProductResponse.builder()
            .sku(sku)
            .name(findProductServiceResponse.getProductDetails().getName())
            .inventory(FindProductResponse.Inventory.builder()
                .quantity(findProductServiceResponse.getInventoryQuantity())
                .warehouses(
                    findProductServiceResponse.getWarehouseList().stream()
                        .map(w -> FindProductResponse.Inventory.Warehouse.builder()
                            .locality(w.getLocality())
                            .quantity(w.getQuantity())
                            .type(w.getType())
                            .build())
                        .collect(Collectors.toList())
                        ).build()
                )
            .isMarketable(findProductServiceResponse.getIsMarketable())
            .build();
    }
}
