package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Bundle;
import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.OrderItem;
import com.norriswu.codetestbundlecalculator.entity.Product;
import com.norriswu.codetestbundlecalculator.utils.CustomHelper;
import com.norriswu.codetestbundlecalculator.utils.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BundleCalculator {
    private final CustomHelper helper = new CustomHelper();

    public void calculate(Order order, List<Product> products) {
        Logger.info("Let's find you a good deal.");
        for (OrderItem item : order.getItems()) {
            products
                    .stream()
                    .filter(product -> product.getFormatCode().equals(item.getFormatCode()))
                    .findFirst()
                    .ifPresent(matchedProduct -> item.setBundles(findBestBundleCombination(item, matchedProduct.getBundles())));
        }
    }

    private Map<Bundle, Integer> findBestBundleCombination(OrderItem orderItem, List<Bundle> bundles) {
        List<Integer> sizeOfBundles = bundles.stream().map(Bundle::getSize).collect(Collectors.toList());
        List<Integer> bundleIndexCombination = helper.findBestCombinationOfNumberGivenList(orderItem.getQuantity(), orderItem.getQuantity(), sizeOfBundles, 0, 0, List.of());

        Map<Bundle, Integer> bundleMap = new HashMap<>();
        bundleIndexCombination.forEach(bundleIndex -> bundleMap.merge(bundles.get(bundleIndex), 1, (currentCount, notUsed) -> ++currentCount));

        return bundleMap;
    }


}
