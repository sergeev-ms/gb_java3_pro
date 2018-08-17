package ru.sms.lesson1.task3;

import java.util.ArrayList;
import java.util.List;

class Box <T extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    void add (T fruit){
        fruits.add(fruit);
    }

    double getWeight(){
        return fruits.stream()
                .mapToDouble(Fruit::getWeight)
                .sum();
    }

    boolean compare(Box box){
        return getWeight() == box.getWeight();
    }

    void moveFruits(Box<T> toBox){
        toBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

    @Override
    public String toString() {
        return "Box{" +
                "fruits=" + fruits +
                '}';
    }
}
