package com.acctmgr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transactionId;
    private String type;
    private double amount;
    private double balanceAfter;
    private LocalDateTime timestamp;

    private static int transactionCounter = 1000;

    public Transaction(String type, double amount, double balanceAfter) {
        this.transactionId = "TXN" + (++transactionCounter);
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Transaction[id=%s, type=%s, amount=%.2f, balance=%.2f, time=%s]",
                transactionId, type, amount, balanceAfter, timestamp.format(formatter));
    }
}
