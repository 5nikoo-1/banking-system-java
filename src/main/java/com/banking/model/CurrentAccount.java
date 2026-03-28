package com.banking.model;

public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount(int id, String owner, double initialBalance, double overdraftLimit) {
        super(id, owner, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (amount > balance + overdraftLimit) {
            throw new com.banking.exception.InsufficientFundsException(
                "Exceeds overdraft limit. Balance: " + balance + ", Limit: " + overdraftLimit);
        }
        balance -= amount;
        System.out.println(owner + " withdrew: " + amount + " | Balance: " + balance);
    }

    @Override
    public String getAccountType() { return "Current Account"; }
}