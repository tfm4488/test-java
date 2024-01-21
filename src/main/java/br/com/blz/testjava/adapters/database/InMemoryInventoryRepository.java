package br.com.blz.testjava.adapters.database;


import br.com.blz.testjava.core.domain.Warehouse;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class InMemoryInventoryRepository {

    private final Map<Long, List<Warehouse>> inventoryMap = new ConcurrentHashMap<>();


    public void save(Long sku, List<Warehouse> warehouseList) {
        inventoryMap.put(sku, warehouseList);
    }

    public Optional<List<Warehouse>> findBySku(long sku) {
        return Optional.ofNullable(inventoryMap.get(sku));
    }


    public void deleteBySku(long sku) {
        inventoryMap.remove(sku);
    }

    public void update(Long sku, List<Warehouse> warehouseList) {
        if (inventoryMap.containsKey(sku)) {
            inventoryMap.put(sku, warehouseList);
        } else {
            throw new NoSuchElementException("Produto com SKU " + sku + " não encontrado.");
        }
    }
}


