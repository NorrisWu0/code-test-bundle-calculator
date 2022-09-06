package com.norriswu.codetestbundlecalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Product {
    private String formatCode;
    private List<Bundle> bundles;

    public void getProductInformation() {
    }

    public Bundle[] findLeastCostOfBundleCombination(Integer total) {
        return new Bundle[0];
    }
}
