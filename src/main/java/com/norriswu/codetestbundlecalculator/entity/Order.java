package com.norriswu.codetestbundlecalculator.entity;

import com.norriswu.codetestbundlecalculator.utils.Logger;
import lombok.Getter;

@Getter
public class Order {
    private int quantity;
    private String formatCode;

    public Order(String[] orderInfo) {
        try {
            this.quantity = Integer.parseInt(orderInfo[0]);
            this.formatCode = orderInfo[1].toUpperCase();
        } catch (NumberFormatException exception) {
            Logger.info(String.format("Boss, \"%s\" is not a number.", orderInfo[0]));
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Logger.info("Boss, you need to tell me what do you need.");
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        return quantity + " " + formatCode;
    }
}
