package com.acctmgr;

public class AccountManager {
    private AccountService accountService;

    public AccountManager() {
        this.accountService = new AccountService();
    }

    public void processDeposit(String accountId, double amount) {
        accountService.getAccount(accountId).ifPresent(account -> {
            if (account.deposit(amount)) {
                System.out.println("Deposit successful: " + amount);
                System.out.println("New balance: " + account.getBalance());
            }
        });
    }

    public void processWithdrawal(String accountId, double amount) {
        accountService.getAccount(accountId).ifPresent(account -> {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful: " + amount);
                System.out.println("New balance: " + account.getBalance());
            }
        });
    }

    public void processTransfer(String fromAccountId, String toAccountId, double amount) {
        accountService.getAccount(fromAccountId).ifPresent(fromAccount -> {
            accountService.getAccount(toAccountId).ifPresent(toAccount -> {
                if (fromAccount.transfer(toAccount, amount)) {
                    System.out.println("Transfer successful: " + amount);
                    System.out.println("From account balance: " + fromAccount.getBalance());
                    System.out.println("To account balance: " + toAccount.getBalance());
                } else {
                    System.out.println("Transfer failed");
                }
            });
        });
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void displaySummary() {
        System.out.println("\n=== Account Manager Summary ===");
        System.out.println("Total Accounts: " + accountService.getTotalAccounts());
        System.out.println("Total Balance: " + accountService.getTotalBalance());
    }
}
