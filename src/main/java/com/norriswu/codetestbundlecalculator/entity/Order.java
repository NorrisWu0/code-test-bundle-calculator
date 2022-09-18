package com.norriswu.codetestbundlecalculator.entity;

import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class Order {
    private final String id;
    private final List<OrderItem> items;


    public Order(List<OrderItem> items) {
        this.id = UUID.randomUUID().toString();
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order: " + items
                .stream()
                .map(OrderItem::toString).collect(Collectors.joining(", ")) + " (id: " + id + ")";
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getSubTotal).sum();
    }


}
