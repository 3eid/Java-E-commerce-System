package com.mohamedeid.services;

import com.mohamedeid.carts.Cart;
import com.mohamedeid.carts.CartItem;
import com.mohamedeid.customers.Customer;

public class Checkout {

    public static void checkout(Customer customer, Cart cart){
        if (cart.isEmpty()){
            throw new IllegalArgumentException("Cart Is Empty!");
        }

        if (!cart.isAllInStock()){
            throw new IllegalArgumentException("Some ordered items are less than stock");
        }

        if (!cart.isAllNotExpired()){
            throw new IllegalArgumentException("Some ordered items are Expired");
        }

        double subTotal = cart.getSubtotal();

        //send shippable items to shipment service and get the total weight
        double totalWeight = Shipping.ship(cart.getShippableItems());

        //calculate shipping fees
        double shippingFees= Shipping.PER_KG_SHIPPING_COST * totalWeight;

        //calculate the grand total
        double grandTotal = subTotal + shippingFees;

        if (grandTotal > customer.getBalance()){
            throw new IllegalArgumentException("Customer balance is insufficient.");
        }

        // update customer balance
        customer.reduceBalanceBy(grandTotal);

        //update products quantity in stock
        for (CartItem item : cart.getItems()){
            item.getProduct().reduceQuantityBy(item.getQuantity());
        }

        System.out.println("** Checkout receipt **");
        for (CartItem item: cart.getItems()){
            System.out.println(item.getQuantity() +"X " + item.getProduct().getName() + "\t" + item.getTotalPrice() );
        }
        System.out.println("----------------------");
        System.out.println("Subtotal \t" + subTotal);
        System.out.println("shipping \t" + shippingFees);
        System.out.println("Amount \t" + grandTotal);





    }


}
