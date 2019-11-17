package com.company;

public class TestMyList extends Test {

    public static void runTests() {
        MyList<Integer> list = new MyList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        check(list.toString().equals("1, 2, 3, 4"));

        list.unshift(0);
        check(list.toString().equals("0, 1, 2, 3, 4"));

        list.shift();
        check(list.toString().equals("1, 2, 3, 4"));

        list.remove(3);
        check(list.toString().equals("1, 2, 4"));

        list.clear();
        check(list.toString().equals(""));
    }

}
