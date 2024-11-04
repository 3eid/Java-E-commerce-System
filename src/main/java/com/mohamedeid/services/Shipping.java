package com.mohamedeid.services;

import com.mohamedeid.carts.CartItem;
import com.mohamedeid.products.Shippable;

import java.util.List;

public class Shipping {
    public static final Double PER_KG_SHIPPING_COST = 15.0;

    public static double ship(List<CartItem> shippableItems){
        System.out.println("** Shipment notice **");
        Shippable shippable;
        double totalWeight = 0;
        for (CartItem item: shippableItems){
            shippable = (Shippable) item.getProduct();
            totalWeight += shippable.getWeight() * item.getQuantity();
            System.out.println(item.getQuantity()+ "x " + shippable.getName() + "\t" +  item.getQuantity()*shippable.getWeight()*1000+"g" );
        }
        System.out.println("Total package weight is " + totalWeight + "Kg\n");
        return totalWeight;
    }
}
