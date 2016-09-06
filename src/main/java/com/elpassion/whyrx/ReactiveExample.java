package com.elpassion.whyrx;

public class ReactiveExample {

    public Observabe<Double> calculate(double input) {
        return Observabe.just(input)
                .map(this::log10)
                .map(this::log10);
    }

    Double log10(Double input) {
        double log10 = Math.log10(input);
        if (Double.isNaN(log10)) {
            throw new IllegalArgumentException();
        }
        return log10;
    }
}
