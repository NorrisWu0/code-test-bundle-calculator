package com.norriswu.codetestbundlecalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Bundle {
    private int size;
    private double price;

    @Override
    public String toString() {
        return size + " pack bundle (pp: $" + price + ")";
    }
}
