package com.norriswu.codetestbundlecalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Bundle {
    private int size;
    private double price;

    public boolean isApplicable(int quantityRequired) {
        return quantityRequired > size;
    }
}
