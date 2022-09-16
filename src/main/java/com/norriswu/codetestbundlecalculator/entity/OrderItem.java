package com.norriswu.codetestbundlecalculator.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class OrderItem {
    private final int quantity;
    private final String formatCode;
    private Map<Bundle, Integer> bundles = new HashMap<>();

    @Override
    public String toString() {
        return quantity + " " + formatCode;
    }

    public double getSubTotal() {
        AtomicReference<Double> subTotal = new AtomicReference<>((double) 0);
        bundles.forEach((bundle, quantity) -> {
            subTotal.updateAndGet(v -> v + bundle.getPrice() * quantity);
        });

        return subTotal.get();
    }

    public String listBundle() {

        return bundles.keySet().stream().map(bundle -> bundles.get(bundle) + " x " + bundle).collect(Collectors.joining(", "));
    }

}
