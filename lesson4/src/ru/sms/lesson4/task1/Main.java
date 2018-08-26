package ru.sms.lesson4.task1;

public class Main {
    static final Object lock = new Object();
    final static MyChar myChar = new MyChar();
    public static void main(String[] args) {
        new Thread(new Printer('A')).start();
        new Thread(new Printer('B')).start();
        new Thread(new Printer('C')).start();
    }
}

class MyChar{
    private volatile char currentChar = 'A';
    private int counter;

    char getCurrentChar() {
        return currentChar;
    }

    synchronized void increment(){
        counter++;
        if (counter % 3 == 0)
            currentChar = 'A';
        else currentChar++;
    }
}

class Printer implements Runnable{
    private char toPrint;

    Printer(char toPrint) {
        this.toPrint = toPrint;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (Main.lock) {
                try {
                    final MyChar myChar = Main.myChar;
                    while (myChar.getCurrentChar() != toPrint) {
                        Main.lock.wait();
                    }
                    System.out.print(toPrint);
                    myChar.increment();
                    Main.lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}