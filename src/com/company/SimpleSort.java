package com.company;

import java.util.Comparator;

public class SimpleSort {

    /**
     * Bubble sort
     */

    public static <T extends Comparable> void bubble(T[] arr) {
        bubble(arr, Comparable::compareTo, 0, arr.length);
    }

    public static <T extends Comparable> void bubble(T[] arr, int from, int to) {
        bubble(arr, Comparable::compareTo, from, to);
    }

    public static <T> void bubble(T[] arr, Comparator<T> comparator) {
        bubble(arr, comparator, 0, arr.length);
    }

    public static <T> void bubble(T[] arr, Comparator<T> comparator, int from, int to) throws IndexOutOfBoundsException {
        if (to < from) throw new IndexOutOfBoundsException();

        for (int i = from; i < to; i++) {
            for (int j = from; j < to + from - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }


    /**
     * Insertion sort
     */

    public static <T extends Comparable> void insertion(T[] arr) {
        insertion(arr, Comparable::compareTo, 0, arr.length);
    }

    public static <T extends Comparable> void insertion(T[] arr, int from, int to) {
        insertion(arr, Comparable::compareTo, from, to);
    }

    public static <T> void insertion(T[] arr, Comparator<T> comparator) {
        insertion(arr, comparator, 0, arr.length);
    }

    public static <T> void insertion(T[] arr, Comparator<T> comparator, int from, int to) throws IndexOutOfBoundsException {
        if (to < from) throw new IndexOutOfBoundsException();

        for (int i = from + 1; i < to; i++) {
            T value = arr[i];

            int j = i - 1;
            for (; j >= 0; j--) {
                if (comparator.compare(value, arr[j]) >= 0) break;

                arr[j + 1] = arr[j];
            }

            arr[j + 1] = value;
        }
    }


    /**
     * Selection sort
     */

    public static <T extends Comparable> void selection(T[] arr) {
        selection(arr, Comparable::compareTo, 0, arr.length);
    }

    public static <T extends Comparable> void selection(T[] arr, int from, int to) {
        selection(arr, Comparable::compareTo, from, to);
    }

    public static <T> void selection(T[] arr, Comparator<T> comparator) {
        selection(arr, comparator, 0, arr.length);
    }

    public static <T> void selection(T[] arr, Comparator<T> comparator, int from, int to) throws IndexOutOfBoundsException {
        if (to < from) throw new IndexOutOfBoundsException();

        for (int i = from; i < to - 1; i++) {
            int iMin = i;

            for (int j = i + 1; j < to; j++) {
                if (comparator.compare(arr[j], arr[iMin]) < 0) {
                    iMin = j;
                }
            }

            T tmp = arr[i];
            arr[i] = arr[iMin];
            arr[iMin] = tmp;
        }
    }

}
