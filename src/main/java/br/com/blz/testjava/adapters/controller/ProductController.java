package br.com.blz.testjava.adapters.controller;

import br.com.blz.testjava.adapters.controller.dto.request.CreateProductRequest;
import br.com.blz.testjava.adapters.controller.dto.request.UpdateProductRequest;
import br.com.blz.testjava.adapters.controller.dto.response.FindProductResponse;
import br.com.blz.testjava.adapters.controller.util.PayloadConverter;
import br.com.blz.testjava.adapters.controller.util.RequestValidator;
import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.usecase.DeleteProductService;
import br.com.blz.testjava.core.usecase.FindProductService;
import br.com.blz.testjava.core.usecase.InsertProductService;
import br.com.blz.testjava.core.usecase.UpdateProductService;
import br.com.blz.testjava.core.usecase.dto.FindProductServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

    @Autowired
    InsertProductService insertProduct;
    @Autowired
    FindProductService findProductService;
    @Autowired
    UpdateProductService updateProductService;
    @Autowired
    DeleteProductService productService;
    @Autowired
    RequestValidator requestValidator;
    @Autowired
    PayloadConverter payloadConverter;
    @Autowired
    DeleteProductService deleteProductService;


    @PostMapping
    public ResponseEntity<String> createProduct (@RequestBody CreateProductRequest createProductRequest){

        requestValidator.validate(createProductRequest);
        log.info("Payload recebido na entrada ", createProductRequest);

        ProductDetails productDetails = payloadConverter.toProductDetailsDomain(createProductRequest);
        List<Warehouse> warehouseList = payloadConverter.toWarehouseDomainList(createProductRequest);

        insertProduct.execute(
            createProductRequest.getSku(),
            productDetails,
            warehouseList
            );
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body("Produto cadastrado com sucesso");
    }

    @GetMapping("/{sku}")
    public ResponseEntity<FindProductResponse> findBySku (@PathVariable Long sku){

        log.info("SKU recebido na entrada ", sku);
        FindProductServiceResponse findProductServiceResponse = findProductService.execute(sku);

        FindProductResponse findProductResponse = payloadConverter.toFindProductResponse(sku, findProductServiceResponse);

        return ResponseEntity.status(HttpStatus.FOUND).body(findProductResponse);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<String> updateProduct (@PathVariable Long sku, @RequestBody UpdateProductRequest updateProductRequest){

        log.info("Payload recebido na entrada ", updateProductRequest);
        requestValidator.validate(updateProductRequest);

        ProductDetails productDetails = payloadConverter.toProductDetailsDomain(updateProductRequest);
        List<Warehouse> warehouseList = payloadConverter.toWarehouseDomainList(updateProductRequest);

        updateProductService.execute(sku, productDetails,warehouseList);
        return ResponseEntity.ok("Produto alterado com sucesso");
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<String> deleteProduct (@PathVariable Long sku){

        log.info("SKU recebido na entrada ", sku);
        deleteProductService.execute(sku);

        return ResponseEntity.ok("Produto deletado com sucesso");
    }


}
