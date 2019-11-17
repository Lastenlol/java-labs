package com.company;

public class MyList<T> {

    private class Node {

        private T data;
        private Node next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node(T data) {
            this(data, null);
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyList() {
        this.size = 0;
    }

    public MyList(MyList<T> original) {
        this.size = original.size;

        if (this.size == 0) return;

        this.head = new Node(original.head.data);
        Node current = head;
        Node currentFromOriginal = original.head;

        while (currentFromOriginal.next != null) {
            currentFromOriginal = currentFromOriginal.next;
            current.next = new Node(currentFromOriginal.data);
            current = current.next;
        }

        this.tail = current;
    }

    // добавляет в начало
    public void unshift(T data) {
        head = new Node(data, head);

        if (size == 0) {
            tail = head;
        }

        ++size;
    }

    // добавляет в конец
    public void add(T data) {
        Node node = new Node(data);

        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }

        ++size;
    }

    // удаляет из начала
    public T shift() {
        return remove(head.data);
    }

    // удаляет по значению
    public T remove(T data) {
        if (size == 0) throw new IndexOutOfBoundsException();

        Node current = new Node(null, head);

        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next == null) throw new NullPointerException();

        Node removingNode = current.next;
        current.next = removingNode.next;

        if (removingNode == head) {
            head = removingNode.next;
        }

        if (removingNode == tail) {
            tail = current;
        }

        --size;

        return removingNode.data;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) return "";

        StringBuilder result = new StringBuilder(head.data.toString());
        Node current = head;

        while (current.next != null) {
            current = current.next;

            result.append(", ");
            result.append(current.data.toString());
        }

        return result.toString();
    }
}
