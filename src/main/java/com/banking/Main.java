package com.banking;

import com.banking.model.*;
import com.banking.service.BankService;

public class Main {

    public static void main(String[] args) {

        BankService bank = new BankService();

        // Factory pattern creates the right account type
        Account nika = BankService.createAccount("savings", 1, "Nika Oragvelidze", 1000.0);
        Account ana  = BankService.createAccount("current", 2, "Ana Beridze", 500.0);

        bank.addAccount(nika);
        bank.addAccount(ana);

        System.out.println("=== Initial Accounts ===");
        bank.printAll();

        System.out.println("\n=== Transactions ===");
        bank.deposit(1, 500);
        bank.withdraw(2, 200);
        bank.transfer(1, 2, 300);

        System.out.println("\n=== After Transactions ===");
        bank.printAll();

        System.out.println("\n=== Testing Overdraft ===");
        bank.withdraw(2, 2000);

        System.out.println("\n=== Applying Interest to Savings ===");
        ((SavingsAccount) nika).applyInterest();

        System.out.println("\n=== Final State ===");
        bank.printAll();
    }
}