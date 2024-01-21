package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.core.domain.ProductDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class InMemoryProductRepository {

    private final Map<Long, ProductDetails> productMap = new ConcurrentHashMap<>();


    public void save(Long sku, ProductDetails productDetails) {
        productMap.put(sku, productDetails);
    }


    public Optional<ProductDetails> findBySku(long sku) {
        return Optional.ofNullable(productMap.get(sku));
    }


    public void deleteBySku(long sku) {
        productMap.remove(sku);
    }

    public void update(Long sku, ProductDetails productDetails) {
        if (productMap.containsKey(sku)) {
            productMap.put(sku, productDetails);
        } else {
            throw new NoSuchElementException("Produto com SKU " + sku + " não encontrado.");
        }
    }
}
