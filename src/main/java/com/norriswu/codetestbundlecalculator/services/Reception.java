package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Order;

import java.util.Scanner;

public class Reception {
    public Order readOrderFromUser() {
        Scanner sc = new Scanner(System.in);
        return new Order(sc.nextLine().split(" "));
    }
}
