package main.CurrencyConversion;

import java.util.HashMap;

/**
 * Класс, на который происходить мэппинг json файла с данными валют.
 */
public class CurrencyConversionBean {
    private String base;
    private HashMap<String, Double> rates;

    public CurrencyConversionBean() {
    }

    public CurrencyConversionBean(String base, HashMap<String, Double> rates) {
        this.base = base;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }
}