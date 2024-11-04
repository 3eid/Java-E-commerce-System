# E-commerce System - Java Project


This project is an e-commerce system designed to support basic functionality like adding products to a cart, checking out, and calculating total costs, including shipping. It demonstrates an inventory management system where products may have specific characteristics, such as expiring, being shippable, or requiring special handling.

## Features

-   **Product Management**: Define products with attributes like name, price, and quantity, with support for expirable and shippable products.
-   **Shopping Cart**: Add products to a cart with specific quantities, ensuring requested quantities are in stock.
-   **Checkout**: Calculate order subtotal, shipping fees, and total paid amount. Display checkout details in the console.
-   **Error Handling**:
    -   Error messages if the cart is empty, the customer's balance is insufficient, a product is out of stock, or expired.
    -   Collect all items that require shipping and send them to `ShippingService` for processing.

## Code Overview

### Product Hierarchy

1.  **Product**: Base class for all products, holding common attributes (`name`, `price`, `quantity`).
2.  **ShippableProduct**: Extends `Product` and implements the `Shippable` interface, adding a `weight` attribute. For example, **TV** and **Headphones** are shippable.
3.  **ExpirableProduct**: Extends `ShippableProduct` with an `expiryDate` attribute to represent perishable products. Examples include **Cheese** and **Biscuits**.
4.  **NonShippableProduct**: Extends `Product`, representing items that don’t require shipping, like **Mobile Scratch Cards**.

### CartItem and Cart

-   **CartItem**: Represents a product added to the cart with a specific quantity. It checks stock availability and calculates the total price.
-   **Cart**: Holds a list of `CartItem` instances, manages subtotal calculation, and validates product stock and expiration status.

### Checkout and Shipping

-   **Checkout**: Handles the checkout process by validating the cart, calculating totals, updating customer balance, and reducing product quantities. If items are shippable, it calculates shipping fees based on weight.
-   **Shipping**: Processes all shippable items in the cart, calculating the total weight and displaying shipping information.

## Project Structure

### Packages, Classes, and Methods

-   **`products` Package**:
    -   `Product`: Base class with attributes like `name`, `price`, and `quantity`.
    -   `ShippableProduct`: Extends `Product`, adds `weight`, and implements the `Shippable` interface.
    -   `ExpirableProduct`: Extends `ShippableProduct`, adding an `expiryDate` and `isExpired()` method.
    -   `NonShippableProduct`: Extends `Product` for items that don’t require shipping.
-   **`carts` Package**:
    -   `CartItem`: Represents a product in the cart with a specified quantity, providing methods to check stock and calculate total price.
    -   `Cart`: Manages a collection of `CartItem`s, providing subtotal calculation, stock validation, and expiration checks.
-   **`services` Package**:
    -   `Checkout`: Contains the main `checkout` method, handling order processing and validation.
    -   `Shipping`: Processes all shippable items, calculating shipping costs based on item weight.
-   **`customers` Package**:
    -   `Customer`: Represents a customer with attributes like `name`, `address`, and `balance`. Provides methods to update balance.

## Code Examples

### Initializations

```java
// Initialize a customer and a cart
Customer customer = new Customer("Mohamed Eid", "Sadat City", 1500.00);
Cart cart = new Cart();

// Initialize inventory

// Non-expired product
LocalDate cheeseExpiryDate = LocalDate.of(2024, 12, 8);
ExpirableProduct cheese = new ExpirableProduct("Cheese", 30, 9, 0.5, cheeseExpiryDate);

// Expired product
LocalDate orangeExpiryDate = LocalDate.of(2024, 10, 29);
ExpirableProduct orange = new ExpirableProduct("Orange", 25, 12, 0.5, orangeExpiryDate);

// Shippable products
ShippableProduct headPhone = new ShippableProduct("HeadPhone", 350, 5, 0.15);
ShippableProduct tv = new ShippableProduct("TV", 2500, 5, 0.15);

// Non-shippable product
NonShippableProduct scratchCard = new NonShippableProduct("ScratchCard", 100, 20);
 
```
### Adding Products to the Cart and Checking Out

```java
// Add items to cart
cart.add(headPhone, 1);
cart.add(headPhone, 1);
cart.add(cheese, 2);
cart.add(scratchCard, 1);

Checkout.checkout(customer, cart);
```

Output:
```
** Shipment notice **
2x HeadPhone    300.0g
2x Cheese       1000.0g
Total package weight is 1.3Kg

** Checkout receipt **
2X HeadPhone    700.0
2X Cheese       60.0
1X ScratchCard  100.0
----------------------
Subtotal        860.0
Shipping        19.5
Amount          879.5 
```

Example 2: Insufficient balance

```java

// Add items to cart
cart.add(headPhone, 1);
cart.add(headPhone, 1);
cart.add(tv, 1);
cart.add(cheese, 2);
cart.add(scratchCard, 1);

Checkout.checkout(customer, cart);
```

Output:

```
Exception in thread "main" java.lang.IllegalArgumentException: 
Customer balance is insufficient.` 
```

Example 3: Expired product


```java
// Add items to cart
cart.add(headPhone, 1);
cart.add(headPhone, 1);
cart.add(orange, 2);
cart.add(cheese, 2);
cart.add(scratchCard, 1);

Checkout.checkout(customer, cart);
``` 

Output:

```
Exception in thread "main" java.lang.IllegalArgumentException: 
Some ordered items are Expired.
``` 

Example 4: Quantity exceeds stock

```java
// Add items to cart
cart.add(headPhone, 1);
cart.add(headPhone, 1);
cart.add(cheese, 20);
cart.add(scratchCard, 1);

Checkout.checkout(customer, cart);` 
```

Output:

```
Exception in thread "main" java.lang.IllegalArgumentException: 
Requested quantity is greater than stock.
```

Example 5: Empty cart checkout

```java
cart.add(headPhone, 1);
cart.add(headPhone, 1);
cart.add(scratchCard, 1);
cart.clearCart();

Checkout.checkout(customer, cart);` 
```
Output:
```
Exception in thread "main" java.lang.IllegalArgumentException:
 Cart Is Empty!
```
