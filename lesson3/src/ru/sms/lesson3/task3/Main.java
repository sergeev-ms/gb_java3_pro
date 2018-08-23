package ru.sms.lesson3.task3;

import java.io.*;

public class Main {
    static final String LESSON3_TASK3_TEXT_FILE = "lesson3/Task3_TextFile.txt";
    static final int PAGE_SIZE = 1800;
    public static void main(String[] args) {
        final RandomFileReader randomFileReader = new RandomFileReader(LESSON3_TASK3_TEXT_FILE);
        int start = 0;
        try {
            while (!randomFileReader.isEndOfFile()) {
                final char[] chars = randomFileReader.readFrom(start, PAGE_SIZE);
                System.out.println(chars);
                start += PAGE_SIZE;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
