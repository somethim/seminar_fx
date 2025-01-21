package com.example.seminar_fx.sorters;

import java.util.Arrays;

public class Sorters {
  public static int[] selectionSort(int[] inputs) {
    int[] arr = Arrays.copyOf(inputs, inputs.length);
    for (int i = 0; i < arr.length - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[minIndex]) {
          minIndex = j;
        }
      }
      int temp = arr[minIndex];
      arr[minIndex] = arr[i];
      arr[i] = temp;
    }
    return arr;
  }

  public static int[] bubbleSort(int[] inputs) {
    int[] arr = Arrays.copyOf(inputs, inputs.length);
    boolean swapped;
    do {
      swapped = false;
      for (int i = 0; i < arr.length - 1; i++) {
        if (arr[i] > arr[i + 1]) {
          int temp = arr[i];
          arr[i] = arr[i + 1];
          arr[i + 1] = temp;
          swapped = true;
        }
      }
    } while (swapped);
    return arr;
  }

  public static int[] radixSort(int[] inputs) {
    int[] arr = Arrays.copyOf(inputs, inputs.length);

    if (arr.length == 0) {
      return arr;
    }

    int max = Arrays.stream(arr).max().orElse(0);

    for (int exp = 1; max / exp > 0; exp *= 10) {
      countingSort(arr, exp);
    }

    return arr;
  }

  private static void countingSort(int[] arr, int exp) {
    int n = arr.length;
    int[] output = new int[n];
    int[] count = new int[10];
    Arrays.fill(count, 0);

    for (int j : arr) {
      count[(j / exp) % 10]++;
    }

    for (int i = 1; i < 10; i++) {
      count[i] += count[i - 1];
    }

    for (int i = n - 1; i >= 0; i--) {
      output[count[(arr[i] / exp) % 10] - 1] = arr[i];
      count[(arr[i] / exp) % 10]--;
    }

    System.arraycopy(output, 0, arr, 0, n);
  }

  public static int[] mergeSort(int[] inputs) {
    int[] arr = Arrays.copyOf(inputs, inputs.length);
    mergeSortHelper(arr, 0, arr.length - 1);
    return arr;
  }

  private static void mergeSortHelper(int[] arr, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      mergeSortHelper(arr, left, mid);
      mergeSortHelper(arr, mid + 1, right);
      merge(arr, left, mid, right);
    }
  }

  private static void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] L = new int[n1];
    int[] R = new int[n2];

    System.arraycopy(arr, left, L, 0, n1);
    System.arraycopy(arr, mid + 1, R, 0, n2);

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
      if (L[i] <= R[j]) {
        arr[k] = L[i];
        i++;
      } else {
        arr[k] = R[j];
        j++;
      }
      k++;
    }

    while (i < n1) {
      arr[k] = L[i];
      i++;
      k++;
    }

    while (j < n2) {
      arr[k] = R[j];
      j++;
      k++;
    }
  }

  public static int[] quickSort(int[] inputs) {
    int[] arr = Arrays.copyOf(inputs, inputs.length);
    quickSortHelper(arr, 0, arr.length - 1);
    return arr;
  }

  private static void quickSortHelper(int[] arr, int low, int high) {
    if (low < high) {
      int pi = partition(arr, low, high);
      quickSortHelper(arr, low, pi - 1);
      quickSortHelper(arr, pi + 1, high);
    }
  }

  private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);

    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }

    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
  }

  public static int[] heapSort(int[] inputs) {
    int[] arr = Arrays.copyOf(inputs, inputs.length);
    int n = arr.length;

    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(arr, n, i);
    }

    for (int i = n - 1; i > 0; i--) {
      int temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;

      heapify(arr, i, 0);
    }

    return arr;
  }

  private static void heapify(int[] arr, int n, int i) {
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < n && arr[left] > arr[largest]) {
      largest = left;
    }

    if (right < n && arr[right] > arr[largest]) {
      largest = right;
    }

    if (largest != i) {
      int swap = arr[i];
      arr[i] = arr[largest];
      arr[largest] = swap;

      heapify(arr, n, largest);
    }
  }
}
