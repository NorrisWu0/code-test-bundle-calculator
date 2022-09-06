package com.norriswu.codetestbundlecalculator.utils;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomHelper {

    /**
     * Apology to whoever provided this on stack overflow, I forgot to include your link here. 🙇
     */
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

    /**
     * Shamelessly stolen from <a href="https://stackoverflow.com/questions/15217438/counting-occurrences-of-a-key-in-a-map-in-java">counting-occurrences-of-a-key-in-a-map-in-java</a>
     */
    public static <K> void count(K key, Map<K, Integer> map) {
        map.merge(key, 1, (currentCount, notUsed) -> ++currentCount);
    }
}
