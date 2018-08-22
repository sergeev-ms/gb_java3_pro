package ru.sms.lesson3.task1;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Main {

    static final String LESSON3_TASK1_RANDOM_FILE_TXT = "lesson3/randomFile.txt";
    static final int ARRAY_SIZE = 50;

    public static void main(String[] args) {
        final byte[] bytes = generateRandomByteArray(ARRAY_SIZE);
        System.out.println("Сгенерировано:          " + Arrays.toString(bytes));
        try {
            generateFile(bytes, LESSON3_TASK1_RANDOM_FILE_TXT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytesFromFile = new byte[ARRAY_SIZE];
        try {
            bytesFromFile = readBytesFromFile(LESSON3_TASK1_RANDOM_FILE_TXT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Прочитано из файла:     " + Arrays.toString(bytesFromFile));
    }

    private static byte[] readBytesFromFile(String fileName) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(fileName);
        byte[] bytes = new byte[ARRAY_SIZE];
        fileInputStream.read(bytes);
        return bytes;
    }

    private static void generateFile(byte[] bytes, String fileName) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    private static byte[] generateRandomByteArray(int size){
        Random random = new Random();
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return bytes;
    }

}
