package ru.sms.lesson3.task3;

import java.io.IOException;
import java.io.RandomAccessFile;

class RandomFileReader {
    private RandomAccessFile file;
    private String path;
    private boolean isEndOfFile;

    RandomFileReader(String path) {
        this.path = path;
    }

    char[] readFrom(int start, int pageSize) throws IOException {
        char[] chars = new char[pageSize];
        file = new RandomAccessFile(path, "r");
        file.seek(start);
        int read = file.read();
        for (int i = 0; i < pageSize && read != -1; i++) {
            chars[i] = (char) read;
            read = file.read();
        }
        file.close();
        if (read == -1)
            isEndOfFile = true;
        return chars;
    }

    boolean isEndOfFile() {
        return isEndOfFile;
    }
}
