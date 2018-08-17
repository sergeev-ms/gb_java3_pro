package ru.sms.lesson1.task1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4};
        String[] strings = {"1", "2", "3", "4"};

        exchangeElements(integers, 2, 3);
        exchangeElements(strings, 1, 2);

        System.out.println(Arrays.toString(integers));
        System.out.println(Arrays.toString(strings));
    }

    private static <T> void exchangeElements(T[] data , int firstIndex, int secondIndex){
        T buffer = data[secondIndex];
        data[secondIndex] = data[firstIndex];
        data[firstIndex] = buffer;
    }
}