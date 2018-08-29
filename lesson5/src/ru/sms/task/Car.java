package ru.sms.task;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private static CyclicBarrier barrier;
    private static CountDownLatch countDownLatch;

    static {
        CARS_COUNT++;
        countDownLatch = Main.countDownLatch;
        barrier = Main.barrier;
    }

    String getName() {
        return name;
    }
    int getSpeed() {
        return speed;
    }
    Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ArrayList<Stage> stages = race.getStages();
        for (Stage stage : stages) {
            stage.go(this);
        }
        countDownLatch.countDown();
    }
}
