package com.company;

import java.util.Comparator;

public class TestSimpleSort {

    public static void main(String[] args) {
        /*
          Числа
         */

        Integer[] arr1i = {5, 32, 1, 6, 8, 3, 1, 5, 8, 9};
        Integer[] arr2i = {5, 32, 1, 6, 8, 3, 1, 5, 8, 9};
        Integer[] arr3i = {5, 32, 1, 6, 8, 3, 1, 5, 8, 9};

        // сначала часть массива
        SimpleSort.bubble(arr1i, 3, 7);
        SimpleSort.insertion(arr2i, 3, 7);
        SimpleSort.selection(arr3i, 3, 7);

        check(arrToString(arr1i).equals("5, 32, 1, 1, 3, 6, 8, 5, 8, 9"));
        check(arrToString(arr2i).equals("5, 32, 1, 1, 3, 6, 8, 5, 8, 9"));
        check(arrToString(arr3i).equals("5, 32, 1, 1, 3, 6, 8, 5, 8, 9"));

        // теперь весь массив
        SimpleSort.bubble(arr1i);
        SimpleSort.insertion(arr2i);
        SimpleSort.selection(arr3i);

        check(arrToString(arr1i).equals("1, 1, 3, 5, 5, 6, 8, 8, 9, 32"));
        check(arrToString(arr2i).equals("1, 1, 3, 5, 5, 6, 8, 8, 9, 32"));
        check(arrToString(arr3i).equals("1, 1, 3, 5, 5, 6, 8, 8, 9, 32"));


        /*
          Сортировка строк лексикографически
         */

        String[] arr1s = {"d", "da", "aqa", "q", "w", "wb", "wa", "a", "b"};
        String[] arr2s = {"d", "da", "aqa", "q", "w", "wb", "wa", "a", "b"};
        String[] arr3s = {"d", "da", "aqa", "q", "w", "wb", "wa", "a", "b"};

        SimpleSort.bubble(arr1s);
        SimpleSort.insertion(arr2s);
        SimpleSort.selection(arr3s);

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

        SimpleSort.bubble(arr1s, comparator);
        SimpleSort.insertion(arr2s, comparator);
        SimpleSort.selection(arr3s, comparator);

        check(arrToString(arr1s).equals("a, b, d, q, w, da, wa, wb, aqa"));
        check(arrToString(arr2s).equals("a, b, d, q, w, da, wa, wb, aqa"));
        check(arrToString(arr3s).equals("a, b, d, q, w, da, wa, wb, aqa"));
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
