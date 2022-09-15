package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Bundle;
import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.OrderItem;
import com.norriswu.codetestbundlecalculator.entity.Product;
import com.norriswu.codetestbundlecalculator.utils.CustomHelper;

import java.util.List;
import java.util.stream.Collectors;

public class BundleCalculator {
    public void calculate(Order order, List<Product> products) {
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
        List<Integer> bundleIndexCombination = new CustomHelper().findBestCombinationOfNumberGivenList(orderItem.getQuantity(), orderItem.getQuantity(), sizeOfBundles, 0, 0, List.of());

        return bundleIndexCombination.stream().map(bundles::get).collect(Collectors.toList());
    }


}
