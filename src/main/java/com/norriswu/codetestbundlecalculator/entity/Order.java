package com.norriswu.codetestbundlecalculator.entity;

import lombok.Getter;

@Getter
public class Order {
    private int quantity;
    private String formatCode;

    public Order(String[] orderInfo) {
        try {
            this.quantity = Integer.parseInt(orderInfo[0]);
            this.formatCode = orderInfo[1];
        } catch (NumberFormatException exception) {
            System.out.println(String.format("Ocelot: Boss, \"%s\" is not a number.", orderInfo[0]));
            exception.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Ocelot: Boss, you need to tell me what do you need.");
            exception.printStackTrace();
        }
    }
}
