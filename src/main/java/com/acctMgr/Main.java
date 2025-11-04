package com.acctmgr;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Account Manager System ===\n");

        AccountManager manager = new AccountManager();
        AccountService service = manager.getAccountService();

        // Create accounts
        service.createAccount("ACC001", "John Doe", 1000.0);
        service.createAccount("ACC002", "Jane Smith", 500.0);
        service.createAccount("ACC003", "Bob Johnson", 2000.0);

        // Perform transactions
        manager.processDeposit("ACC001", 250.0);
        manager.processWithdrawal("ACC002", 100.0);
        manager.processTransfer("ACC003", "ACC001", 300.0);

        // Display information
        service.listAllAccounts();
        service.printAccountStatement("ACC001");
        manager.displaySummary();

        // Close an account
        service.closeAccount("ACC002");

        System.out.println("\n=== Program Complete ===");
    }
}
