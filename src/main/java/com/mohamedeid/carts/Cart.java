package com.mohamedeid.carts;

import com.mohamedeid.products.ExpirableProduct;
import com.mohamedeid.products.Product;
import com.mohamedeid.products.ShippableProduct;

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
                return;
            }
        }

        // If product is not in the cart, add it as a new CartItem
        items.add(new CartItem(product, quantity));
    }

    public double getSubtotal(){
        double subTotal = 0;
        for (CartItem item:items){
            subTotal += item.getTotalPrice();
        }
        return subTotal;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public List<CartItem> getShippableItems() {
        List<CartItem> shippableItems = new ArrayList<>();
        for (CartItem item: items){
            if (item.getProduct() instanceof ShippableProduct ){
                shippableItems.add(item);
            }
        }
        return shippableItems;
    }

    public List<CartItem> getExpirableItems() {
        List<CartItem> expirableItems = new ArrayList<>();
        for (CartItem item: items){
            if (item.getProduct() instanceof ExpirableProduct){
                expirableItems.add(item);
            }
        }
        return expirableItems;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void clearCart(){
        items.clear();
    }

    public boolean isAllInStock(){
        for (CartItem item: items){
            if (!item.isInStock()) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllNotExpired (){
        ExpirableProduct expirableProduct;
        for (CartItem item: getExpirableItems()){
            expirableProduct = (ExpirableProduct) item.getProduct();

            if (expirableProduct.isExpired()){
                return false;
            }
        }
        return true;
    }
}

