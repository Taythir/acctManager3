package com.acctmgr;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountService {
    private Map<String, Account> accounts;

    public AccountService() {
        this.accounts = new HashMap<>();
    }

    public Account createAccount(String accountId, String accountHolder, double initialBalance) {
        if (accounts.containsKey(accountId)) {
            System.out.println("Account already exists: " + accountId);
            return null;
        }
        Account account = new Account(accountId, accountHolder, initialBalance);
        accounts.put(accountId, account);
        System.out.println("Account created: " + account);
        return account;
    }

    public Optional<Account> getAccount(String accountId) {
        return Optional.ofNullable(accounts.get(accountId));
    }

    public boolean deleteAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account not found: " + accountId);
            return false;
        }
        if (account.getBalance() != 0) {
            System.out.println("Cannot delete account with non-zero balance");
            return false;
        }
        accounts.remove(accountId);
        System.out.println("Account deleted: " + accountId);
        return true;
    }

    public boolean closeAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account not found: " + accountId);
            return false;
        }
        account.setActive(false);
        System.out.println("Account closed: " + accountId);
        return true;
    }

    public void listAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found");
            return;
        }
        System.out.println("\n=== All Accounts ===");
        accounts.values().forEach(System.out::println);
    }

    public void printAccountStatement(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account not found: " + accountId);
            return;
        }
        System.out.println("\n=== Account Statement ===");
        System.out.println(account);
        System.out.println("\nTransactions:");
        account.getTransactions().forEach(System.out::println);
    }

    public int getTotalAccounts() {
        return accounts.size();
    }

    public double getTotalBalance() {
        return accounts.values().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }
}
