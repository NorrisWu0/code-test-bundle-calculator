package com.norriswu.codetestbundlecalculator;

import com.norriswu.codetestbundlecalculator.entity.Bundle;
import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.Product;
import com.norriswu.codetestbundlecalculator.services.BundleCalculator;
import com.norriswu.codetestbundlecalculator.services.Inventory;
import com.norriswu.codetestbundlecalculator.services.Reception;
import com.norriswu.codetestbundlecalculator.utils.Logger;

import java.util.List;

public class MediaStoreApplication {
    private static final Product img = new Product("IMG", List.of(new Bundle(10, 800), new Bundle(5, 450)));
    private static final Product flac = new Product("FLAC", List.of(new Bundle(9, 1147.5), new Bundle(6, 810), new Bundle(3, 427.5)));
    private static final Product vid = new Product("VID", List.of(new Bundle(9, 1530), new Bundle(5, 900), new Bundle(3, 570)));

    public static void main(String[] args) {
        Logger.info("What do you need boss? Let me know in this format 👉 \"15 IMG\"");
        Order order = new Reception().readOrderFromUser();

        Logger.info("Okay boss, you need " + order + "...");
        Logger.info("Let's see if we have it in stock, gimme a sec...");
        Product availableProduct = new Inventory().checkProductAvailability(order, List.of(img, flac, vid));
        if (availableProduct == null) {
            Logger.info("Sorry boss, I don't think we have " + order.getFormatCode() + " in our lineup");
            System.exit(1);
        }

        Logger.info("Got it, we do have " + order.getFormatCode() + " in our line up");
        Logger.info("Let's find you a good deal.");
        new BundleCalculator().calculate(order, availableProduct);
        if (order.getMatchedBundles().isEmpty()) {
            Logger.info("Mmmm, sorry boss, I don't have any bundle that will make up a good deal for you. Maybe try somewhere else?");
            System.exit(1);
        }

        Logger.info("Okay boss, here's the best deal we can find for you.");
        Logger.info("Getting you " + order + " will cost $" + order.getTotal());
        Logger.info("Which is a combination of " + order.getBundleInfo());

    }
}