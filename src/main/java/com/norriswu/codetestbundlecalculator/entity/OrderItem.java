package com.norriswu.codetestbundlecalculator.entity;

import com.norriswu.codetestbundlecalculator.utils.CustomHelper;
import com.norriswu.codetestbundlecalculator.utils.Logger;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Data
public class OrderItem {
    private final CustomHelper helper = new CustomHelper();
    private int quantity;
    private String formatCode;
    private Map<Bundle, Integer> bundles;

    public OrderItem(String[] orderInfo) {
        try {
            this.quantity = Integer.parseInt(orderInfo[0]);
            this.formatCode = orderInfo[1].toUpperCase();
        } catch (NumberFormatException exception) {
            Logger.info(String.format("Boss, \"%s\" is not a number.", orderInfo[0]));
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Logger.info("Boss, you need to tell me what do you need.");
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        return quantity + " " + formatCode;
    }

    public double getSubTotal() {
        AtomicReference<Double> subTotal = new AtomicReference<>((double) 0);
        bundles.forEach((bundle, quantity) -> {
            subTotal.updateAndGet(v -> (double) (v + bundle.getPrice() * quantity));
        });

        return subTotal.get();
    }

    public String listBundle() {

        return bundles.keySet().stream().map(bundle -> bundles.get(bundle) + " x " + bundle).collect(Collectors.joining(", "));
    }

}
