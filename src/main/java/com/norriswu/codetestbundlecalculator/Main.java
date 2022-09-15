package com.norriswu.codetestbundlecalculator;

import com.norriswu.codetestbundlecalculator.entity.Bundle;
import com.norriswu.codetestbundlecalculator.entity.Product;
import com.norriswu.codetestbundlecalculator.services.BundleCalculator;

import java.util.List;

public class Main {
    private static final Product img = new Product("IMG", List.of(new Bundle(10, 800), new Bundle(5, 450)));
    private static final Product flac = new Product("FLAC", List.of(new Bundle(9, 1147.5), new Bundle(6, 810), new Bundle(3, 427.5)));
    private static final Product vid = new Product("VID", List.of(new Bundle(9, 1530), new Bundle(5, 900), new Bundle(3, 570)));
    private static final BundleCalculator calculator = new BundleCalculator(List.of(img, flac, vid));

    public static void main(String[] args) {
        calculator.init();
    }



}