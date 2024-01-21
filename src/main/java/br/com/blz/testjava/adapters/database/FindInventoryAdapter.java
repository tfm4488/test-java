package br.com.blz.testjava.adapters.database;

import br.com.blz.testjava.adapters.database.InMemoryInventoryRepository;
import br.com.blz.testjava.core.domain.Warehouse;
import br.com.blz.testjava.core.ports.FindInventoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindInventoryAdapter implements FindInventoryPort {

    @Autowired
    private InMemoryInventoryRepository inMemoryInventoryRepository;
    @Override
    public Optional<List<Warehouse>> find(Long sku) {
        return inMemoryInventoryRepository.findBySku(sku);
    }
}
