package com.company;

public class TestMyVector extends Test {

    public static void runTests() {
        MyVector<Integer> list = new MyVector<>();

        check(list.size() == 0);
        check(list.capacity() == 10);

        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        check(list.toString().equals("1, 2, 3, 4"));

        list.insert(0, 0);
        check(list.toString().equals("0, 1, 2, 3, 4"));

        list.insert(15, 2);
        check(list.toString().equals("0, 1, 15, 2, 3, 4"));

        check(list.capacity() == 10);

        list.push(5);
        list.push(6);
        list.push(7);
        list.push(8);

        check(list.size() == 10);
        check(list.capacity() == 10);

        list.push(9);
        list.push(10);

        check(list.size() == 12);
        check(list.capacity() == 32);

        list.pop();
        check(list.size() == 11);
        check(list.toString().equals("0, 1, 15, 2, 3, 4, 5, 6, 7, 8, 9"));

        list.remove(2);
        check(list.size() == 10);
        check(list.toString().equals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9"));

        list.setSize(5);
        check(list.size() == 5);
        check(list.toString().equals("0, 1, 2, 3, 4"));

        list.clear();
        check(list.size() == 0);
        check(list.capacity() == 32);
        check(list.toString().equals(""));
    }

}
