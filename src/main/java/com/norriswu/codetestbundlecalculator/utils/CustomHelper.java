package com.norriswu.codetestbundlecalculator.utils;

import com.norriswu.codetestbundlecalculator.entity.Bundle;

import java.util.ArrayList;
import java.util.List;
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

    public static List<Integer> findBestCombinationOfNumberGivenList(int required, int currentRemaining, List<Integer> list, int startIndex, int currentIndex, List<Integer> currentCombination) {
        List<Integer> combination = new ArrayList<>(currentCombination);
        int nextBundleIndex = currentIndex;

        if (currentRemaining == 0) return combination;

        boolean exhaustedPossibleCombinationWithStartingIndex = currentRemaining > 0 && currentIndex == list.size();
        if (exhaustedPossibleCombinationWithStartingIndex) {
            int startingBundleIndex = startIndex + 1;
            return findBestCombinationOfNumberGivenList(required, required, list, startingBundleIndex, startingBundleIndex, List.of());
        }

        int remaining = currentRemaining - list.get(currentIndex);

        if (remaining < 0) {
            if (startIndex == list.size() - 1) {
                return List.of();
            }
            nextBundleIndex++;
            remaining = currentRemaining;
        } else {
            combination.add(currentIndex);
        }

        return findBestCombinationOfNumberGivenList(
                required,
                remaining,
                list,
                startIndex,
                nextBundleIndex,
                combination
        );
    }
}
