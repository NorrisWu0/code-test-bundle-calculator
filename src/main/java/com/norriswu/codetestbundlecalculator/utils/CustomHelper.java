package com.norriswu.codetestbundlecalculator.utils;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomHelper {

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

    public static <K> void count(K key, Map<K, Integer> map) {
        map.merge(key, 1, (currentCount, notUsed) -> ++currentCount);
    }
}
