package com.company;

import java.io.*;
import java.util.Comparator;
import java.util.Random;

public class TestEffectiveSort {

    private static Integer[] getArrOfRandomIntegers() {
        Integer[] randomNumbers = new Integer[10000];
        Random random = new Random();

        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = random.nextInt();
        }

        return randomNumbers;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String[] strings = input.split(" ");

        long startTime = System.nanoTime();

        EffectiveSort.mergeSort(strings);

        System.out.println((System.nanoTime() - startTime) + " nanoseconds");

        /*for (int i = 0; i < strings.length; i++) {
            strings[i] = System.console().readLine();
        }*/


        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }


//        long startTime = 0;
        Integer[] arr = null;

        // bubble sort
        arr = getArrOfRandomIntegers();
        startTime = System.nanoTime();
        SimpleSort.bubble(arr);
        System.out.println((System.nanoTime() - startTime) / 1000000);

        // quick sort
        arr = getArrOfRandomIntegers();
        startTime = System.nanoTime();
        EffectiveSort.quickSort(arr);
        System.out.println((System.nanoTime() - startTime) / 1000000);

        /*
          Числа
         */

        Integer[] arr1i = {5, 32, 1, 6, 8, 3, 1, 5, 8, 9};
        Integer[] arr2i = {5, 32, 1, 6, 8, 3, 1, 5, 8, 9};
        Integer[] arr3i = {5, 32, 1, 6, 8, 3, 1, 5, 8, 9};

        // сначала часть массива
        EffectiveSort.quickSort(arr1i, 3, 6);
        EffectiveSort.heapSort(arr2i, 3, 6);
        EffectiveSort.mergeSort(arr3i, 3, 6);

        check(arrToString(arr1i).equals("5, 32, 1, 1, 3, 6, 8, 5, 8, 9"));
        check(arrToString(arr2i).equals("5, 32, 1, 1, 3, 6, 8, 5, 8, 9"));
        check(arrToString(arr3i).equals("5, 32, 1, 1, 3, 6, 8, 5, 8, 9"));

        // теперь весь массив
        EffectiveSort.quickSort(arr1i);
        EffectiveSort.heapSort(arr2i);
        EffectiveSort.mergeSort(arr3i);

        check(arrToString(arr1i).equals("1, 1, 3, 5, 5, 6, 8, 8, 9, 32"));
        check(arrToString(arr2i).equals("1, 1, 3, 5, 5, 6, 8, 8, 9, 32"));
        check(arrToString(arr3i).equals("1, 1, 3, 5, 5, 6, 8, 8, 9, 32"));


        /*
          Сортировка строк лексикографически
         */

        String[] arr1s = {"d", "da", "aqa", "q", "w", "wb", "wa", "a", "b"};
        String[] arr2s = {"d", "da", "aqa", "q", "w", "wb", "wa", "a", "b"};
        String[] arr3s = {"d", "da", "aqa", "q", "w", "wb", "wa", "a", "b"};

        EffectiveSort.quickSort(arr1s);
        EffectiveSort.heapSort(arr2s);
        EffectiveSort.mergeSort(arr3s);

        check(arrToString(arr1s).equals("a, aqa, b, d, da, q, w, wa, wb"));
        check(arrToString(arr2s).equals("a, aqa, b, d, da, q, w, wa, wb"));
        check(arrToString(arr3s).equals("a, aqa, b, d, da, q, w, wa, wb"));


        /*
          Сортировка строк по длине и лексикографически (если длины равны) со своим компаратором
         */

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int result = a.length() - b.length();

                return (result == 0) ? a.compareTo(b) : result;
            }
        };

        EffectiveSort.quickSort(arr1s, comparator);
        EffectiveSort.heapSort(arr2s, comparator);
        EffectiveSort.mergeSort(arr3s, comparator);

        check(arrToString(arr1s).equals("a, b, d, q, w, da, wa, wb, aqa"));
        check(arrToString(arr2s).equals("a, b, d, q, w, da, wa, wb, aqa"));
        check(arrToString(arr3s).equals("a, b, d, q, w, da, wa, wb, aqa"));

        System.out.println(arrToString(arr1s));
    }

    protected static void check(boolean statement) {
        check(statement, "");
    }

    protected static void check(boolean statement, String message) {
        if (!statement) throw new AssertionError(message);
    }

    protected static <T> String arrToString(T[] arr) {
        if (arr.length == 0) return "";

        StringBuilder result = new StringBuilder(arr[0].toString());

        for (int i = 1; i < arr.length; i++) {
            result.append(", ");
            result.append(arr[i].toString());
        }

        return result.toString();
    }

}
