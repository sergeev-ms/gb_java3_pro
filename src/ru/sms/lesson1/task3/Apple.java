package ru.sms.lesson1.task3;

class Apple extends Fruit {
    private float weight;

    Apple() {
        this.weight = 1.0f;
    }
    @Override
    float getWeight() {
        return weight;
    }
}
