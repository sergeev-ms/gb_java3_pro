package ru.sms.lesson1.task3;

class Orange extends Fruit {
    private float weight;

    Orange() {
        this.weight = 1.5f;
    }

    @Override
    float getWeight() {
        return weight;
    }
}
