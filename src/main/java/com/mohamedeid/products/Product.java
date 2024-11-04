package com.mohamedeid.products;

public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int q) {
        if (q<0){
            throw new IllegalArgumentException("Quantity can't be negative!")
        }
        else {
            this.quantity = q;
        }
    }

    public void reduceQuantityBy(int amount){
        setQuantity(this.getQuantity() - amount);
    }

}