package com.company;

import java.lang.reflect.Array;

public class MyVector<T> {

    private T[] items;
    private int size;
    private int capacity;

    public MyVector(int size) {
        if (size < 0) throw new NegativeArraySizeException();

        this.size = size;
        this.capacity = this.getDesiredCapacity();

        this.items = (T[]) new Object[this.capacity];
    }

    public MyVector() {
        this(0);
    }

    public MyVector(MyVector<T> original) {
        this.size = original.size;
        this.capacity = original.capacity;
        this.items = (T[]) new Object[original.items.length];

        System.arraycopy(original.items, 0, this.items, 0, original.items.length);
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        return items[index];
    }

    public void insert(T data, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        increaseSize(1);

        System.arraycopy(items, index, items, index + 1, size - 1 - index);
        items[index] = data;
    }

    public void push(T data) {
        insert(data, size);
    }

    public void pop() {
        if (size == 0) throw new IndexOutOfBoundsException();

        items[size--] = null;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        System.arraycopy(items, index + 1, items, index, size - index);
        pop();
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public void setSize(int newSize) {
        if (newSize <= 0) throw new IndexOutOfBoundsException();
        if (newSize == size) return;

        if (newSize > size) {
            increaseSize(newSize - size);
        } else {
            int elementsCountToRemove = size - newSize;

            for (int i = 0; i < elementsCountToRemove; i++) {
                pop();
            }
        }
    }

    private void increaseSize(int amount) {
        size += Math.max(0, amount);
        updateCapacity();
    }

    private void updateCapacity() {
        if (size <= capacity) return;

        capacity = getDesiredCapacity();
        T[] newItems = (T[]) Array.newInstance(items.getClass().getComponentType(), capacity);

        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    private int getDesiredCapacity() {
        return Math.max(capacity, size * 2 + 10);
    }

    @Override
    public String toString() {
        if (size == 0) return "";

        StringBuilder result = new StringBuilder(items[0].toString());

        for (int i = 1; i < size; i++) {
            result.append(", ");
            result.append(items[i].toString());
        }

        return result.toString();
    }

}
