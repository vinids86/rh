package com.xerpa.acme.resources.report;

import java.time.Duration;
import java.time.LocalDate;

public class History {
    private LocalDate day;
    private Duration balance;

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Duration getBalance() {
        return balance;
    }

    public void setBalance(Duration balance) {
        this.balance = balance;
    }
}
