package com.banking.model;

public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(int id, String owner, double initialBalance, double interestRate) {
        super(id, owner, initialBalance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest applied: " + interest + " | New balance: " + balance);
    }

    @Override
    public String getAccountType() { return "Savings Account"; }
}