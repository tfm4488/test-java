package br.com.blz.testjava.core.usecase.impl;

import br.com.blz.testjava.core.ports.DeleteProductPort;
import br.com.blz.testjava.core.ports.FindProductPort;
import br.com.blz.testjava.core.usecase.DeleteProductService;
import br.com.blz.testjava.core.usecase.FindProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductServiceImpl implements DeleteProductService {

    @Autowired
    FindProductPort findProductPort;

    @Autowired
    DeleteProductPort deleteProductPort;
    @Override
    public void execute(Long sku) {

        if (!findProductPort.find(sku).isPresent()){
            throw new RuntimeException("O Produto com o sku " + sku + " não existe");
        }

        deleteProductPort.execute(sku);
    }
}
