package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class SortThread implements Runnable {
    private int[] array;
    private final int sortOrder;

    public SortThread(int[] array, int sortOrder) {
        this.array = array;
        this.sortOrder = sortOrder;
    }

    @Override
    public void run() {
        if (sortOrder == 1) {
            Arrays.sort(array);
        } else {
            Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
            Arrays.sort(boxedArray, Comparator.reverseOrder());
            for (int i = 0; i < array.length; i++) {
                array[i] = boxedArray[i];
            }
        }
    }
}