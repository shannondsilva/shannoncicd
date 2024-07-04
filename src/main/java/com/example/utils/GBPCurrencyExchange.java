package com.example.utils;

public enum GBPCurrencyExchange {

    GBP("British Pound Sterling", 1.0),
    USD("US Dollar", 1.32),
    EUR("Euro", 1.16),
    JPY("Japanese Yen", 148.06),
    AUD("Australian Dollar", 1.79),
    CAD("Canadian Dollar", 1.68);

    private String fullName;
    private double exchangeRateToGBP;

    // Constructor
    GBPCurrencyExchange(String fullName, double exchangeRateToGBP) {
        this.fullName = fullName;
        this.exchangeRateToGBP = exchangeRateToGBP;
    }

    // Getter methods
    public String getFullName() {
        return fullName;
    }

    public double getExchangeRateToGBP() {
        return exchangeRateToGBP;
    }
}
