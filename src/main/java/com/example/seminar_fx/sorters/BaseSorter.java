package com.example.seminar_fx.sorters;

import java.util.function.Function;

public class BaseSorter {
  public static long run(int[] inputs, Function<int[], int[]> sortFunction) {
    System.out.println("Running sorters for input size of: " + inputs.length);
    long startTime = System.currentTimeMillis();
    System.out.println("Start: " + startTime);
    sortFunction.apply(inputs);
    long endTime = System.currentTimeMillis();
    System.out.println("End: " + endTime);
    return endTime - startTime;
  }
}
