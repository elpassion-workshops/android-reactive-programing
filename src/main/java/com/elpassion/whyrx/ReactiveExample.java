package com.elpassion.whyrx;

import java.util.concurrent.Executor;

public class ReactiveExample {

    private final Executor executor;

    public ReactiveExample(Executor executor) {
        this.executor = executor;
    }

    public Observabe<Double> calculate(double input) {
        return Observabe.just(input)
                .map(this::log10)
                .map(this::log10)
                .subscribeOn(executor);
    }

    private Double log10(Double input) {
        double log10 = Math.log10(input);
        if (Double.isNaN(log10)) {
            throw new IllegalArgumentException();
        }
        return log10;
    }
}
