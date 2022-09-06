package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.Product;
import com.norriswu.codetestbundlecalculator.utils.Logger;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class BundleCalculator {
    private List<Product> products;

    public void init() {
        Logger.info("Ocelot: What do you need boss? Let me know in this format 👉 \"15 IMG\"");

        Logger.info("Boss: Give me... ");
        Scanner sc = new Scanner(System.in);

        Order order = new Order(sc.nextLine().split(" "));
        if (order.getFormatCode() != null) {
            Logger.info(String.format("Ocelot: Okay boss, you need %1$s of %2$s let's see what the best deal is, gimme a sec...", order.getQuantity(), order.getFormatCode()));
        }
    }
}
