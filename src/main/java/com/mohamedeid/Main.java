package com.mohamedeid;

import com.mohamedeid.carts.Cart;
import com.mohamedeid.customers.Customer;
import com.mohamedeid.products.ExpirableProduct;
import com.mohamedeid.products.NonShippableProduct;
import com.mohamedeid.products.ShippableProduct;
import com.mohamedeid.services.Checkout;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // initials a customer and a cart
        Customer customer = new Customer("Mohamed Eid", "Sadat City", 1500.00);
        Cart cart = new Cart();

        // initialies inventory

        //create non-expired product
        LocalDate cheeseExpiryDate = LocalDate.of(2024, 12, 8);
        ExpirableProduct cheese = new ExpirableProduct("Cheese   ", 30,9,0.5, cheeseExpiryDate );

        //expired product
        LocalDate orangeExpiryDate = LocalDate.of(2024, 10, 29);
        ExpirableProduct orange = new ExpirableProduct("Orange   ", 25,12,0.5, orangeExpiryDate );

        //shippable products
        ShippableProduct headPhone = new ShippableProduct("HeadPhone", 350, 5, 0.15);
        ShippableProduct tv = new ShippableProduct("tv", 2500, 5, 0.15);

        //nonshippable product
        NonShippableProduct scratchCard = new NonShippableProduct("ScratchCard", 100, 20);


        // add to cart
        cart.add(headPhone, 1);
        cart.add(headPhone, 1);
//        cart.add(cheese, 20);

        cart.add(scratchCard, 1);
//        cart.clearCart();

        // checkout

        Checkout.checkout(customer,cart);

    }
}