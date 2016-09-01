package com.elpassion.whyrx;

import java.util.concurrent.Executor;

public class CallbackExample {
    private final Executor executor;

    public CallbackExample(Executor executor) {
        this.executor = executor;
    }

    public void calculate(final Double input, final Callback<Double> onSuccess, final Callback<Exception> onError) {
        calculateLog10(input, new Callback<Double>() {
            public void call(Double result) {
                calculateLog10(result, onSuccess, onError);
            }
        }, onError);
    }

    private void calculateLog10(final Double input, final Callback<Double> onSuccess, final Callback<Exception> onError) {
        executor.execute(new Runnable() {
            public void run() {
                try {
                    onSuccess.call(log10(input));
                } catch (Exception e) {
                    onError.call(e);
                }
            }
        });
    }

    private Double log10(Double input) {
        double log10 = Math.log10(input);
        if (Double.isNaN(log10)) {
            throw new IllegalArgumentException();
        }
        return log10;
    }
}
