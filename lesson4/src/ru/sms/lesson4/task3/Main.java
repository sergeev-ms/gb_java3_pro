package ru.sms.lesson4.task3;

public class Main {
    public static void main(String[] args){
        final MFP mfp = new MFP();
        mfp.print(50);
        mfp.print(20);
        mfp.scan(40);
        mfp.scan(30);
    }
}
class MFP {
    private volatile boolean isPrinting;
    private volatile boolean isScanning;
    private final Object printerLock = new Object();
    private final Object scannerLock = new Object();

    void print(int pages) {
        new Thread(() -> {
            synchronized (printerLock){
                while (isPrinting) {
                    try {
                        printerLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isPrinting = true;
                for (int i = 1; i <= pages; i++) {
                    System.out.println(String.format("Отпечатано %s страницы из %s", i, pages));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isPrinting = false;
                printerLock.notifyAll();
            }
        }).start();
    }

    void scan(int pages){
        new Thread(() -> {
            synchronized (scannerLock){
                while (isScanning) {
                    try {
                        scannerLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isScanning = true;
                for (int i = 1; i <= pages; i++) {
                    System.out.println(String.format("  Отсканировано %s страницы из %s", i, pages));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isScanning = false;
                scannerLock.notifyAll();
            }
        }).start();
    }
}
