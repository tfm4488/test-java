package br.com.blz.testjava.core.ports;

import br.com.blz.testjava.core.domain.Warehouse;

import java.util.List;
import java.util.Optional;

public interface FindInventoryPort {
    Optional<List<Warehouse>> find (Long sku);
}

