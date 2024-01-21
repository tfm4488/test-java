package br.com.blz.testjava.core.usecase;

import br.com.blz.testjava.core.usecase.dto.FindProductServiceResponse;

public interface FindProductService {

    FindProductServiceResponse execute (Long sku);
}
