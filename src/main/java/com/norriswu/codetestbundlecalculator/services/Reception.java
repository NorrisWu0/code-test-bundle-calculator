package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.OrderItem;
import com.norriswu.codetestbundlecalculator.utils.Logger;

import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Reception {
    public Order readOrderFromUser() {
        Logger.info("Hey boss, let me know what you need? If you need more than one item, just separate them with comma. e.g 👉 \"10 img, 15 flac, 13 vid\"");
        Scanner sc = new Scanner(System.in);


        List<OrderItem> orderItems = Stream
                .of(sc.nextLine().split(","))
                .map(String::trim)
                .map(orderInfo -> {
                    OrderInfo info = validateOrder(orderInfo.split(" "));
                    return new OrderItem(info.quantity, info.formatCode);
                })
                .collect(Collectors.toList());
        Order order = new Order(orderItems);

        Logger.info("Okay boss, this is what you have ordered. [" + order + "]");

        return order;
    }

    private OrderInfo validateOrder(String[] orderInfo) {
        try {
            OrderInfo info = new OrderInfo();
            info.quantity = Integer.parseInt(orderInfo[0]);
            info.formatCode = orderInfo[1].toUpperCase();

            return info;
        } catch (NumberFormatException exception) {
            Logger.info(String.format("Boss, \"%s\" is not a number.", orderInfo[0]));
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Logger.info("Boss, you need to tell me what do you need.");
            System.exit(1);
        }

        return null;
    }

    public void summariseOrder(Order order) {
        Logger.info("Okay boss, here's the best deal we can find for you.");
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        Logger.info(order + " will cost you " + currency.format(order.getTotal()) + ".");
        Logger.info("In which...");

        List<OrderItem> orderItems = order.getItems();

        IntStream.range(0, orderItems.size()).forEach(index -> {
                    OrderItem item = orderItems.get(index);
                    Logger.info("Item " + index + ": " + item + " will cost " + currency.format(item.getSubTotal()) + " with " + item.listBundle() + ".");
                }
        );


    }

    class OrderInfo {
        public int quantity;
        public String formatCode;
    }
}
