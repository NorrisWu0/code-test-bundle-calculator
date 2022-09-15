package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.Product;

import java.util.List;

public class Inventory {

    public Product checkProductAvailability(Order order, List<Product> products) {
        return products.stream().filter(_product -> order.getFormatCode().equals(_product.getFormatCode())).findFirst().orElse(null);
    }
}
