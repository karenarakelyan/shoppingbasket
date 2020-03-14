package com.karen.shoppingbasket.dto.order;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Karen Arakelyan
 */

public class OrderDto {

    private final Map<Long, MutableInt> productQuantityMap;

    public OrderDto() {
        this.productQuantityMap = new HashMap<>();
    }

    public void addProduct(final Long id, final Long quantity) {
        if (this.productQuantityMap.containsKey(id)) {
            productQuantityMap.get(id).add(quantity);
        } else {
            productQuantityMap.put(id, new MutableInt(quantity));
        }
    }

    public Map<Long, Integer> getProductsWithQuantities() {
        return productQuantityMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue()));
    }


}
