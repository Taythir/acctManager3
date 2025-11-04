package com.acctmgr;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountId;
    private String accountHolder;
    private double balance;
    private List<Transaction> transactions;
    private boolean isActive;

    public Account(String accountId, String accountHolder, double initialBalance) {
        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        this.isActive = true;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive");
            return false;
        }
        if (!isActive) {
            System.out.println("Account is inactive");
            return false;
        }
        balance += amount;
        Transaction transaction = new Transaction("DEPOSIT", amount, balance);
        transactions.add(transaction);
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return false;
        }
        if (!isActive) {
            System.out.println("Account is inactive");
            return false;
        }
        if (balance < amount) {
            System.out.println("Insufficient funds");
            return false;
        }
        balance -= amount;
        Transaction transaction = new Transaction("WITHDRAWAL", amount, balance);
        transactions.add(transaction);
        return true;
    }

    public boolean transfer(Account targetAccount, double amount) {
        if (this.withdraw(amount)) {
            if (targetAccount.deposit(amount)) {
                return true;
            } else {
                // Rollback withdrawal
                this.deposit(amount);
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Account[id=%s, holder=%s, balance=%.2f, active=%s]",
                accountId, accountHolder, balance, isActive);
    }
}
