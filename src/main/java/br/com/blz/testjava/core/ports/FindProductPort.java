package br.com.blz.testjava.core.ports;

import br.com.blz.testjava.core.domain.ProductDetails;
import java.util.Optional;

public interface FindProductPort {

    Optional<ProductDetails> find (Long sku);
}
