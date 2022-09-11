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

    public static List<Integer> findBestCombinationOfNumberGivenList(int required, int remaining, List<Integer> list, int startIndex, int currentIndex, List<Integer> combination) {
        List<Integer> _combination = new ArrayList<>(combination);
        int _nextBundleIndex = currentIndex;

        if (remaining == 0) return _combination;

        boolean exhaustedPossibleCombinationWithStartingIndex = remaining > 0 && currentIndex == list.size();
        if (exhaustedPossibleCombinationWithStartingIndex) {
            int _startingBundleIndex = startIndex + 1;
            return findBestCombinationOfNumberGivenList(required, required, list, _startingBundleIndex, _startingBundleIndex, List.of());
        }

        int _remaining = remaining - list.get(currentIndex);

        if (_remaining < 0) {
            if (startIndex == list.size() - 1) {
                return List.of();
            }
            _nextBundleIndex++;
            _remaining = remaining;
        } else {
            _combination.add(currentIndex);
        }

        return findBestCombinationOfNumberGivenList(
                required,
                _remaining,
                list,
                startIndex,
                _nextBundleIndex,
                _combination
        );
    }
}
