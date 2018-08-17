package ru.sms.lesson1.task2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4};
        String[] strings = {"5", "6", "7", "8"};
        final List<Integer> integersList = convertArrayToList(integers);
        final List<String> stringList = convertArrayToList(strings);
        System.out.println(integersList);
        System.out.println(stringList);
    }

    private static <T> List<T> convertArrayToList(T[] array){
        List<T> list = new ArrayList<>();
        for (T element : array)
            list.add(element);
        return list;
    }
}

