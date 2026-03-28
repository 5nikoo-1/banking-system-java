package com.banking.service;

import com.banking.model.*;
import com.banking.exception.InsufficientFundsException;
import java.util.HashMap;
import java.util.Map;

public class BankService {

    // Factory pattern — creates accounts by type
    public static Account createAccount(String type, int id, String owner, double balance) {
        return switch (type.toLowerCase()) {
            case "savings" -> new SavingsAccount(id, owner, balance, 3.5);
            case "current" -> new CurrentAccount(id, owner, balance, 500.0);
            default -> throw new IllegalArgumentException("Unknown account type: " + type);
        };
    }

    private final Map<Integer, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
    }

    public Account getAccount(int id) {
        Account account = accounts.get(id);
        if (account == null) throw new RuntimeException("Account not found: " + id);
        return account;
    }

    public void deposit(int id, double amount) {
        getAccount(id).deposit(amount);
    }

    public void withdraw(int id, double amount) {
        try {
            getAccount(id).withdraw(amount);
        } catch (InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void transfer(int fromId, int toId, double amount) {
        getAccount(fromId).transfer(getAccount(toId), amount);
    }

    public void printAll() {
        accounts.values().forEach(System.out::println);
    }
}