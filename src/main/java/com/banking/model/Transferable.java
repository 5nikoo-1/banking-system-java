package com.banking.model;

public interface Transferable {
    void deposit(double amount);
    void withdraw(double amount);
    void transfer(Account target, double amount);
}