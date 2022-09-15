package com.norriswu.codetestbundlecalculator.services;

import com.norriswu.codetestbundlecalculator.entity.Order;
import com.norriswu.codetestbundlecalculator.entity.OrderItem;
import com.norriswu.codetestbundlecalculator.utils.Logger;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reception {
    public Order readOrderFromUser() {
        Logger.info("Hey boss, let me know what you need? If you need more than one item, just separate them with comma. e.g 👉 \"10 img, 15 flac, 13 vid\"");
        Scanner sc = new Scanner(System.in);
        List<OrderItem> orderItems = Stream
                .of(sc.nextLine().split(","))
                .map(String::trim)
                .map(item -> new OrderItem(item.split(" ")))
                .collect(Collectors.toList());

        return new Order(orderItems);
    }
}
