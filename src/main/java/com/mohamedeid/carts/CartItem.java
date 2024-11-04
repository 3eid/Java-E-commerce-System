package com.mohamedeid.carts;

import com.mohamedeid.products.Product;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        if (quantity< 0){
            throw new IllegalArgumentException("Quantity can't be negative");
        }
        this.quantity = quantity;
    }

    /** returns the total price of this quantity of the product */
    public double getTotalPrice(){
        return product.getPrice() * quantity;
    }

    /**returns true if the requested quantity of the product is available in stock */
    public boolean isInStock(){
        if (quantity <= product.getQuantity()){
            return true;
        }
        return false;
    }
}
