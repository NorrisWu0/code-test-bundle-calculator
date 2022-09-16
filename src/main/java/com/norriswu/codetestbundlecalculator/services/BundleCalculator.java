package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Bundle;
import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.OrderItem;
import com.norriswu.codetestbundlecalculator.entity.Product;
import com.norriswu.codetestbundlecalculator.utils.CustomHelper;
import com.norriswu.codetestbundlecalculator.utils.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class BundleCalculator {
    private final CustomHelper helper = new CustomHelper();

    public void calculate(Order order, List<Product> products) {
        Logger.info("Let's find you a good deal.");
        order.getItems().forEach(item -> {
                    products
                            .stream()
                            .filter(product -> product.getFormatCode().equals(item.getFormatCode()))
                            .findFirst()
                            .ifPresent(matchedProduct -> item.setMatchedBundles(findBestBundleCombination(item, matchedProduct.getBundles())));
                }
        );
    }

    private List<Bundle> findBestBundleCombination(OrderItem orderItem, List<Bundle> bundles) {
        List<Integer> sizeOfBundles = bundles.stream().map(Bundle::getSize).collect(Collectors.toList());
        List<Integer> bundleIndexCombination = helper.findBestCombinationOfNumberGivenList(orderItem.getQuantity(), orderItem.getQuantity(), sizeOfBundles, 0, 0, List.of());

        return bundleIndexCombination.stream().map(bundles::get).collect(Collectors.toList());
    }


}
