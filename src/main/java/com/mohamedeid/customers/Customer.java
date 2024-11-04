package com.mohamedeid.customers;

public class Customer {
    private String name;
    private String address;
    private double balance;



    public Customer(String name, String address, double balance) {
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    String getName(){return this.name;}
    public String getAddress() {return address;}
    public double getBalance() {return balance;}

    public void setBalance(double new_balance) {
        if (new_balance >= 0 ){
            this.balance = new_balance;
        }
        else {
            throw new IllegalArgumentException("Balance can't be negative.");
        }
    }

    /** reduce the balance of a customer by a given amount (if checkout) */
    public void reduceBalanceBy(double amount){
        setBalance(getBalance() - amount);
    }

    /** increases the balance of a customer by a given amount (if recharged) */
    public void increaseBalanceBy(double amount){
        setBalance(getBalance() + amount);
    }
}
