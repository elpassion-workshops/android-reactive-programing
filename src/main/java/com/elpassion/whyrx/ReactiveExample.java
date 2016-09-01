package com.elpassion.whyrx;

import java.util.concurrent.Executor;

public class ReactiveExample {
    private final Executor executor;

    public ReactiveExample(Executor executor) {
        this.executor = executor;
    }

    public Observable<Double> calculate(final Double input) {
        return Observable.just(input)
                .map(new Function<Double, Double>() {
                    public Double apply(Double input) {
                        return log10(input);
                    }
                })
                .map(new Function<Double, Double>() {
                    public Double apply(Double input) {
                        return log10(input);
                    }
                })
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
