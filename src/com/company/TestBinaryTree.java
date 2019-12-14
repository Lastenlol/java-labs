package com.company;

import java.io.*;

public class TestBinaryTree {

    public static void main(String[] args) throws IOException {
        BinaryTree<Integer> treeFromFile = new BinaryTree<>();

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String numbersString = reader.readLine();

        String[] rawNumbers = numbersString.split(", ");
        Integer[] numbers = new Integer[rawNumbers.length];

        for (int i = 0; i < rawNumbers.length; i++) {
            numbers[i] = Integer.parseInt(rawNumbers[i]);
        }



        for (int i = 0; i < numbers.length; i++) {
            treeFromFile.insert(Integer.parseInt(rawNumbers[i]));
        }

        check(treeFromFile.countLeafs() == 5);

        BinaryTree<Integer> tree = new BinaryTree<>();

        check(tree.size() == 0);

        tree.insert(11);
        tree.insert(5);
        tree.insert(21);
        tree.insert(51);
        tree.insert(13);

        check(tree.countLeafs() == 3);

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
