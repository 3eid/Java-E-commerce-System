package com.mohamedeid.carts;

import com.mohamedeid.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity){
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Requested quantity is greater than stock.");
        }

        // Check if the product is already in the cart
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                // Update the quantity in the existing CartItem
                int newQuantity = item.getQuantity() + quantity;
                if (newQuantity > product.getQuantity()) {
                    throw new IllegalArgumentException("Total quantity exceeds stock.");
                }
                item.setQuantity(newQuantity);
                return; // Exit the method as we have updated the quantity
            }
        }

        // If product is not in the cart, add it as a new CartItem
        items.add(new CartItem(product, quantity));
    }

}
