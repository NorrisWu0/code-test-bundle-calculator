package com.norriswu.codetestbundlecalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Order {
    private List<OrderItem> items;

    @Override
    public String toString() {
        return items
                .stream()
                .map(OrderItem::toString).collect(Collectors.joining(", "));
    }

    public void getSummary() {
        double total = items.stream().mapToDouble(OrderItem::getTotal).sum();
        return "This order comes in at a whooping " + NumberFormat.getCurrencyInstance().format(total);
    }
}
