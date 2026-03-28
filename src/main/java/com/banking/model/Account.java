package com.banking.model;

import com.banking.exception.InsufficientFundsException;

public abstract class Account implements Transferable {

    protected int id;
    protected String owner;
    protected double balance;

    public Account(int id, String owner, double initialBalance) {
        this.id = id;
        this.owner = owner;
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");
        balance += amount;
        System.out.println(owner + " deposited: " + amount + " | Balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (amount > balance) throw new InsufficientFundsException(
            "Insufficient funds. Balance: " + balance + ", Requested: " + amount);
        balance -= amount;
        System.out.println(owner + " withdrew: " + amount + " | Balance: " + balance);
    }

    @Override
    public void transfer(Account target, double amount) {
        this.withdraw(amount);
        target.deposit(amount);
        System.out.println("Transferred " + amount + " from " + owner + " to " + target.owner);
    }

    public double getBalance() { return balance; }
    public String getOwner()   { return owner; }
    public int getId()         { return id; }

    public abstract String getAccountType();

    @Override
    public String toString() {
        return getAccountType() + " | ID: " + id + " | Owner: " + owner + " | Balance: " + balance;
    }
}