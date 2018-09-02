/*
1. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
Метод должен вернуть новый массив, который получен путем вытаскивания элементов из исходного массива, идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, в противном случае в методе необходимо выбросить RuntimeException.
Написать набор тестов для этого метода (3-4 варианта входных данных).
вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ]

2. Написать метод, который проверяет, что массив состоит только из чисел 1 и 4.
Если в массиве нет хоть одной 4 или 1, то метод вернет false;
Написать набор тестов для этого метода (3-4 варианта входных данных).
 */

package ru.sms.lesson6.task1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App
{
    public static void main( String[] args ){
        final int[] ints = {1, 2, 3, 3, 2, 1, 1, 3, 4};
        System.out.println(Arrays.toString(createNewArrayAfterNum(ints)));
    }

    static int[] createNewArrayAfterNum(int[] ints){
        final int num = 4;
        int positionOfNum;
        for (positionOfNum = ints.length - 1; positionOfNum >= 0; positionOfNum--) {
            if (ints[positionOfNum] == num)
                break;
        }
        if (positionOfNum == -1)
            throw new RuntimeException("There is no " + num + " in the array");
        final int newLength = ints.length - positionOfNum - 1;
        int[] newInts = new int[newLength];
        for (int i = ints.length - 1, j = newInts.length - 1; i > positionOfNum; i--, j--) {
            newInts[j] = ints[i];
        }
        return newInts;
    }

    static boolean checkForSomeNumbers(int[] ints){
        final Set<Integer> integers = new HashSet<>(Arrays.asList(2, 4));
        boolean contains = true;
        for (int anInt : ints) {
            contains = integers.contains(anInt);
            if (!contains)
                break;
        }
        return contains;
    }
}