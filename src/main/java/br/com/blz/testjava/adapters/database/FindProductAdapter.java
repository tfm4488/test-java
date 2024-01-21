package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.core.domain.ProductDetails;
import br.com.blz.testjava.core.ports.FindProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class FindProductAdapter implements FindProductPort {

    @Autowired
    private InMemoryProductRepository productRepository;

    @Override
    public Optional<ProductDetails> find(Long sku) {
        return productRepository.findBySku(sku);
    }
}
