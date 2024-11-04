package com.mohamedeid.carts;

import com.mohamedeid.products.ExpirableProduct;
import com.mohamedeid.products.Product;
import com.mohamedeid.products.ShippableProduct;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    /** create a CartItem and add it to the CartItems list */
    public void add(Product product, int quantity){
        //check if available in stock
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

    /** calculates subtotal and return it */
    public double getSubtotal(){
        double subTotal = 0;
        for (CartItem item:items){
            subTotal += item.getTotalPrice();
        }
        return subTotal;
    }

    /** returns all CartItems in cart */
    public List<CartItem> getItems() {
        return items;
    }

    /** returns all ShippableCartItems in cart */
    public List<CartItem> getShippableItems() {
        List<CartItem> shippableItems = new ArrayList<>();
        for (CartItem item: items){
            if (item.getProduct() instanceof ShippableProduct ){
                shippableItems.add(item);
            }
        }
        return shippableItems;
    }

    /** returns all ExpirableCartItems in cart */
    public List<CartItem> getExpirableItems() {
        List<CartItem> expirableItems = new ArrayList<>();
        for (CartItem item: items){
            if (item.getProduct() instanceof ExpirableProduct){
                expirableItems.add(item);
            }
        }
        return expirableItems;
    }

    /** returns true if cart is empty */
    public boolean isEmpty(){
        return items.isEmpty();
    }

    /** clear all items in the cart */
    public void clearCart(){
        items.clear();
    }

    /** returns true if all requested quantities are available in stock */
    public boolean isAllInStock(){
        for (CartItem item: items){
            if (!item.isInStock()) {
                return false;
            }
        }
        return true;
    }

    /** returns true if all requested products are not expired */
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

