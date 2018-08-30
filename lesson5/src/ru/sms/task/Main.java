/*
Организуем гонки:
        Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого их них уходит разное время.
        В тоннель не может заехать одновременно больше половины участников (условность).
        Попробуйте все это синхронизировать.
        Только после того, как все завершат гонку, нужно выдать объявление об окончании.
        Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.
*/
package ru.sms.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    static final int CARS_COUNT = 4;
    static final CountDownLatch countDownLatchFinish = new CountDownLatch(CARS_COUNT);
    static final CountDownLatch countDownLatchReady = new CountDownLatch(CARS_COUNT);
    static final CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(80), new Road(40));
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < CARS_COUNT; i++) {
            final int randomSpeed = 20 + (int) (Math.random() * 10);
            final Car car = new Car(race, randomSpeed);
            cars.add(car);
        }
        final ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
        executorService.invokeAll(cars);

        countDownLatchReady.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        countDownLatchFinish.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

