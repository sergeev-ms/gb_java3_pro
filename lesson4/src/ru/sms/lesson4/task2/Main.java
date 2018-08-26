package ru.sms.lesson4.task2;

import java.io.*;

public class Main {

    public static void main(String[] args){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("lesson4/output.txt"))) {
            final Thread first = new Thread(() -> {
                try {
                    printToFile(bufferedWriter, "Первый");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            final Thread second = new Thread(() -> {
                try {
                    printToFile(bufferedWriter, "Второй");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            final Thread third = new Thread(() -> {
                try {
                    printToFile(bufferedWriter, "Третий");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            first.start();
            second.start();
            third.start();
            first.join();
            second.join();
            third.join();
            bufferedWriter.flush();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void printToFile(BufferedWriter stream, String string) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            stream.write(string);
            stream.newLine();
            Thread.sleep(20);
        }
    }
}

