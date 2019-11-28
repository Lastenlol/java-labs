package com.company;

public class BinaryTree<T extends Comparable> {

    private class Node {

        private T data;
        private Node left;
        private Node right;

        Node(T data) {
            this.data = data;
        }

        Node(Node original) {
            this.data = original.data;

            if (original.left != null) this.left = new Node(original.left);
            if (original.right != null) this.right = new Node(original.right);
        }
    }

    private Node root;
    private int size;

    public BinaryTree() {
        this.size = 0;
    }

    public BinaryTree(T rootData) {
        this.root = new Node(rootData);
        this.size = 1;
    }

    public BinaryTree(BinaryTree<T> original) {
        this.size = original.size;
        this.root = new Node(original.root);
    }

    private Node findNode(T data, Node from) {
        if (from == null) return null;
        if (from.data == data) return from;

        Node nextNode = data.compareTo(from.data) < 0 ? from.left : from.right;

        return findNode(data, nextNode);
    }

    // поиск элемента
    public boolean contains(T data) {
        return findNode(data, root) != null;
    }

    private void insert(T data, Node parent) {
        var shouldBeLeftNode = data.compareTo(parent.data) < 0;
        var nextNode = shouldBeLeftNode ? parent.left : parent.right;

        if (nextNode != null) {
            insert(data, nextNode);
        } else {
            var newNode = new Node(data);

            if (shouldBeLeftNode) parent.left = newNode;
            else parent.right = newNode;
        }
    }

    // вставка элемента
    public void insert(T data) {
        if (root != null) insert(data, root);
        else root = new Node(data);

        size++;
    }

    public void clear() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

}
