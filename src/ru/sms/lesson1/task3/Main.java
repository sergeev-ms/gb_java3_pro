package ru.sms.lesson1.task3;

public class Main {
    public static void main(String[] args) {
        final Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        final double appleBoxWeight = appleBox.getWeight();
        System.out.println("appleBoxWeight: " + appleBoxWeight);

        final Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        final double orangeBoxWeight = orangeBox.getWeight();
        System.out.println("orangeBoxWeight: " + orangeBoxWeight);

        System.out.println("appleBoxWeight = orangeBoxWeight: " + appleBox.compare(orangeBox));

        final Box<Apple> appleBox2 = new Box<>();
        appleBox2.add(new Apple());

        appleBox.moveFruits(appleBox2);

        System.out.println("appleBox2: " + appleBox2);
        System.out.println("appleBox: " + appleBox);
    }
}
