package com.company;

public class TestBinaryTree {

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        check(tree.size() == 0);

        tree.insert(11);
        tree.insert(5);
        tree.insert(21);
        tree.insert(51);
        tree.insert(13);

        check(tree.size() == 5);
        check(tree.contains(11));
        check(!tree.contains(12));

        BinaryTree<Integer> copy = new BinaryTree<>(tree);

        check(copy.size() == 5);

        copy.insert(2);
        copy.insert(8);

        check(tree.size() == 5);
        check(copy.size() == 7);
        check(copy.contains(2));
        check(copy.contains(8));
        check(!tree.contains(2));
        check(!tree.contains(8));

        tree.clear();

        check(tree.size() == 0);
        check(copy.size() == 7);
    }

    protected static void check(boolean statement) {
        check(statement, "");
    }

    protected static void check(boolean statement, String message) {
        if (!statement) throw new AssertionError(message);
    }

}
