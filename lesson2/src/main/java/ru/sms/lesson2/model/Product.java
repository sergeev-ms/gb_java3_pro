package ru.sms.lesson2.model;

public class Product {
    private int prodId;
    private String title;
    private int cost;

    public Product(int prodId, String title, int cost) {
        this.prodId = prodId;
        this.title = title;
        this.cost = cost;
    }

    public int getProdId() {
        return prodId;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
