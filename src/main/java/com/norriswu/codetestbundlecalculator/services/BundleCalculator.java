package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Bundle;
import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.Product;
import com.norriswu.codetestbundlecalculator.utils.CustomHelper;

import java.util.List;
import java.util.stream.Collectors;

public class BundleCalculator {
    public void calculate(Order order, Product product) {
        order.setMatchedBundles(findBestBundleCombination(order, product.getBundles()));
    }

    private List<Bundle> findBestBundleCombination(Order order, List<Bundle> bundles) {
        List<Integer> sizeOfBundles = bundles.stream().map(Bundle::getSize).collect(Collectors.toList());
        List<Integer> bundleIndexCombination = new CustomHelper().findBestCombinationOfNumberGivenList(order.getQuantity(), order.getQuantity(), sizeOfBundles, 0, 0, List.of());

        return bundleIndexCombination.stream().map(bundles::get).collect(Collectors.toList());
    }


}
