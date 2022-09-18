package com.norriswu.codetestbundlecalculator.utils;

import java.util.ArrayList;
import java.util.List;

public class CustomHelper {
    public List<Integer> findBestCombinationOfNumberGivenList(int required, int currentRemaining, List<Integer> list, int startIndex, int currentIndex, List<Integer> currentCombination) {
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
