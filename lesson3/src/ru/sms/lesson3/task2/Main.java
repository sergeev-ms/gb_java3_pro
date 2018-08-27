package ru.sms.lesson3.task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Main {
    static final String LESSON3_TASK2_RANDOM_FILE_TXT = "lesson3/Task2_randomFile%s.txt";
    static final String LESSON3_TASK2_SUM_OF_FILES = "lesson3/Task2_SunOfFiles.txt";
    static final int ARRAY_SIZE = 100;
    public static final int FILES_COUNT = 5;

    public static void main(String[] args) {

        generateFiles();

        List<FileInputStream> inputStreams = new ArrayList<>();
        for (int i = 1; i <= FILES_COUNT; i++) {
            try {
                inputStreams.add(new FileInputStream(String.format(LESSON3_TASK2_RANDOM_FILE_TXT, i)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        final Enumeration<FileInputStream> enumeration = Collections.enumeration(inputStreams);
        SequenceInputStream sis = new SequenceInputStream(enumeration);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(LESSON3_TASK2_SUM_OF_FILES);
            int read = sis.read();
            while (read != -1){
                fileOutputStream.write(read);
                read = sis.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                sis.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateFiles() {
        for (int i = 1; i <= FILES_COUNT; i++) {
            byte[] bytes = ru.sms.lesson3.task1.Main.generateRandomByteArray(ARRAY_SIZE);
            try {
                ru.sms.lesson3.task1.Main.generateFile(bytes, String.format(LESSON3_TASK2_RANDOM_FILE_TXT, i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
