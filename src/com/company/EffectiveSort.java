package com.company;

import java.util.Comparator;

public class EffectiveSort {

    // Быстрая
    public static <T extends Comparable> void quickSort(T[] arr) {
        quickSort(arr, Comparable::compareTo, 0, arr.length - 1);
    }

    public static <T extends Comparable> void quickSort(T[] arr, int from, int to) {
        quickSort(arr, Comparable::compareTo, from, to);
    }

    public static <T> void quickSort(T[] arr, Comparator<T> comparator) {
        quickSort(arr, comparator, 0, arr.length - 1);
    }

    public static <T> void quickSort(T[] arr, Comparator<T> comparator, int from, int to) throws IndexOutOfBoundsException {
        if (from < 0 || to >= arr.length) throw new IndexOutOfBoundsException();

        // обозначаем наши границы, изначально они равны from и to
        int left = from;
        int right = to;

        if (left >= right) return;

        // выбираем опорный элемент
        T pivot = arr[(to + from) / 2];

        while (left <= right) {
            // двигаем каждую из границ, пока элемент в них не окажется больше/меньше либо равен опорному
            while (comparator.compare(arr[left], pivot) < 0) {
                left++;
            }
            while (comparator.compare(arr[right], pivot) > 0) {
                right--;
            }

            if (left >= right) break;

            // если такое произошло, и left < right, то элементы не на своих местах
            // поэтому меняем их местами
            T tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;

            left++;
            right--;
        }

        // рекурсивно повторяем алгоритм для левой и правой части, разделённых относительно опорного элемента
        quickSort(arr, comparator, from, right);
        quickSort(arr, comparator, right + 1, to);
    }

    // Пирамидальная
    public static <T extends Comparable> void heapSort(T[] arr) {
        heapSort(arr, Comparable::compareTo, 0, arr.length - 1);
    }

    public static <T extends Comparable> void heapSort(T[] arr, int from, int to) {
        heapSort(arr, Comparable::compareTo, from, to);
    }

    public static <T> void heapSort(T[] arr, Comparator<T> comparator) {
        heapSort(arr, comparator, 0, arr.length - 1);
    }

    public static <T> void heapSort(T[] arr, Comparator<T> comparator, int from, int to) throws IndexOutOfBoundsException {
        if (from < 0 || to >= arr.length) throw new IndexOutOfBoundsException();

        // строим кучу
        for (int i = (from + to + 1) / 2 - 1; i >= from; i--) {
            heapify(arr, to + 1, i, from, comparator);
        }

        // извлекаем элементы из кучи
        for (int i = to; i >= from; i--) {
            // перемещаем текущий корень (самый большой элемент) в конец
            T tmp = arr[from];
            arr[from] = arr[i];
            arr[i] = tmp;

            // и вызываем heapify на уменьшенной куче
            heapify(arr, i, from, from, comparator);
        }
    }

    private static <T> void heapify(T[] arr, int n, int root, int from, Comparator<T> comparator) {
        // здесь мы преобразуем элементы в двоичную кучу поддерева с корнем i

        int largest = root; // сначала считаем, что корень – наибольший элемент
        int left = root * 2 + 1 - from; // индекс левого потомка
        int right = root * 2 + 2 - from; // индекс правого потомка

        // вычисляем индекс наибольшего значения из тройки i, left, right
        if (left < n && comparator.compare(arr[left], arr[largest]) > 0) largest = left;
        if (right < n && comparator.compare(arr[right], arr[largest]) > 0) largest = right;

        // если наибольшее значение и корень не совпадают
        if (largest != root) {
            // меняем их местами
            T tmp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = tmp;

            // и преобразовываем в кучу поддерево с корнем в largest (это либо левый, либо правый потомок)
            heapify(arr, n, largest, from, comparator);
        }
    }

    // Слиянием
    public static <T extends Comparable> void mergeSort(T[] arr) {
        mergeSort(arr, Comparable::compareTo, 0, arr.length - 1);
    }

    public static <T extends Comparable> void mergeSort(T[] arr, int from, int to) {
        mergeSort(arr, Comparable::compareTo, from, to);
    }

    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        mergeSort(arr, comparator, 0, arr.length - 1);
    }

    public static <T> void mergeSort(T[] arr, Comparator<T> comparator, int from, int to) throws IndexOutOfBoundsException {
        if (from < 0 || to >= arr.length) throw new IndexOutOfBoundsException();
        if (from >= to) return;

        int middle = (from + to) / 2;

        // сортируем левую и правую части
        mergeSort(arr, comparator, from, middle);
        mergeSort(arr, comparator, middle + 1, to);

        // и сливаем их в одну
        merge(arr, from, middle, to, comparator);
    }

    private static <T> void merge(T[] arr, int left, int middle, int right, Comparator<T> comparator) {
        // вычисляем размеры левого и правого массивов
        int leftArrLength = middle - left + 1;
        int rightArrLength = right - middle;

        // создаём левый и правый массивы
        T[] leftArr = (T[]) new Object[leftArrLength];
        T[] rightArr = (T[]) new Object[rightArrLength];

        // и копируем в них значения из оригинального массива
        System.arraycopy(arr, left, leftArr, 0, leftArrLength);
        System.arraycopy(arr, middle + 1, rightArr, 0, rightArrLength);

        // счётчики для левого и правого массивов
        int i = 0;
        int j = 0;
        // счётчик для оригинального массива (определяет место вставки элементов)
        int k = left;

        while (i < leftArrLength && j < rightArrLength) {
            if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }

            k++;
        }

        // дозаписываем оставшиеся элементы из левого массива
        while (i < leftArrLength) {
            arr[k] = leftArr[i];

            i++;
            k++;
        }

        // дозаписываем оставшиеся элементы из правого массива
        while (j < rightArrLength) {
            arr[k] = rightArr[j];

            j++;
            k++;
        }
    }


    public static void mergeSort(String[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(String[] arr, int from, int to) throws IndexOutOfBoundsException {
        if (from < 0 || to >= arr.length) throw new IndexOutOfBoundsException();
        if (from >= to) return;

        int middle = (from + to) / 2;

        // сортируем левую и правую части
        mergeSort(arr, from, middle);
        mergeSort(arr, middle + 1, to);

        // и сливаем их в одну
        merge(arr, from, middle, to);
    }

    private static void merge(String[] arr, int left, int middle, int right) {
        // вычисляем размеры левого и правого массивов
        int leftArrLength = middle - left + 1;
        int rightArrLength = right - middle;
        // создаём левый и правый массивы
        String[] leftArr = new String[leftArrLength];
        String[] rightArr = new String[rightArrLength];
        // и копируем в них значения из оригинального массива
        System.arraycopy(arr, left, leftArr, 0, leftArrLength);
        System.arraycopy(arr, middle + 1, rightArr, 0, rightArrLength);
        // счётчики для левого и правого массивов
        int i = 0;
        int j = 0;
        int k = left; // счётчик для оригинального массива (определяет место вставки элементов)

        while (i < leftArrLength && j < rightArrLength) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // дозаписываем оставшиеся элементы из левого массива
        while (i < leftArrLength) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // дозаписываем оставшиеся элементы из правого массива
        while (j < rightArrLength) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

}
