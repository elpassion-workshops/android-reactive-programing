package com.elpassion.whyrx;

public class Calculator {

    Double calculate(Double input) {
        return log10(log10(input));
    }

    private Double log10(Double input) {
        double log10 = Math.log10(input);
        if (Double.isNaN(log10)) {
            throw new IllegalArgumentException();
        }
        return log10;
    }
}
