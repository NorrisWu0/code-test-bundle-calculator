package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Bundle;
import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.Product;
import com.norriswu.codetestbundlecalculator.utils.CustomHelper;
import com.norriswu.codetestbundlecalculator.utils.JSON;
import com.norriswu.codetestbundlecalculator.utils.Logger;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BundleCalculator {
    private List<Product> products;

    public void init() {
        Order order = readOrderFromUser();

        Logger.info(String.format("Okay boss, you need " + order + "..."));
        Logger.info("Let's see if we have it in stock, gimme a sec...");

        Product matchedProduct = checkProductAvailability(order);
        List<Bundle> bestBundleCombination = findBestBundleCombination(order, matchedProduct.getBundles());

        if (!bestBundleCombination.equals(List.of())) {
            generateResponse(order, bestBundleCombination);
        } else {
            Logger.info("Mmmm, sorry boss, I don't have any bundle that will make up a good deal for you. Maybe try somewhere else?");
        }
    }

    private Order readOrderFromUser() {
        Logger.info("What do you need boss? Let me know in this format 👉 \"15 IMG\"");
        Scanner sc = new Scanner(System.in);
        return new Order(sc.nextLine().split(" "));
    }

    private Product checkProductAvailability(Order order) {
        try {
            Product product = products.stream().filter(_product -> order.getFormatCode().equals(_product.getFormatCode())).collect(CustomHelper.toSingleton());
            Logger.info(String.format("Got it, we do have %s in our line up", order.getFormatCode()));
            Logger.info("Let's find you a good deal.");

            return product;
        } catch (IllegalStateException exception) {
            Logger.info(String.format("Sorry boss, I don't think we have %s in our stock", order.getFormatCode()));
            System.exit(1);
            return null;
        }
    }

    private List<Bundle> findBestBundleCombination(Order order, List<Bundle> bundles) {
        List<Integer> sizeOfBundles = bundles.stream().map(Bundle::getSize).collect(Collectors.toList());
        List<Integer> bundleIndexCombination = CustomHelper.findBestCombinationOfNumberGivenList(order.getQuantity(), order.getQuantity(), sizeOfBundles, 0, 0, List.of());

        return bundleIndexCombination.stream().map(bundles::get).collect(Collectors.toList());
    }

    private double calculateTotalOfBundles(List<Bundle> bundles) {
        return bundles.stream().mapToDouble(Bundle::getPrice).sum();
    }

    private void generateResponse(Order order, List<Bundle> combination) {
        Logger.info("Okay boss, here's the best deal we can find for you.");
        double total = calculateTotalOfBundles(combination);
        Map<String, Integer> bundleMap = new HashMap<>();
        combination.forEach(bundle -> CustomHelper.count(bundle.toString(), bundleMap));

        String combinationString = bundleMap.keySet().stream().map(bundle -> bundleMap.get(bundle) + " x " + bundle).collect(Collectors.joining(", "));

        Logger.info("Getting you " + order + " will cost $" + total);
        Logger.info("Which is a combination of " + combinationString);
    }

}
