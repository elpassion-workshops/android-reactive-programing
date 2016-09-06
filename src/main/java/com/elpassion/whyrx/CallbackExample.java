package com.elpassion.whyrx;

import java.util.concurrent.Executor;

public class CallbackExample {

    private final Executor executor;

    public CallbackExample(Executor executor) {
        this.executor = executor;
    }

    void calculate(Double input, final Callback<Double> onSuccess, final Callback<Throwable> onError) {
        calculateLog10(input, result -> calculateLog10(result, onSuccess, onError), onError);
    }

    private void calculateLog10(Double input, Callback<Double> onSuccess, Callback<Throwable> onError) {
        executor.execute(() -> {
            try {
                onSuccess.call(log10(input));
            } catch (Exception e) {
                onError.call(e);
            }
        });
    }

    Double log10(Double input) {
        double log10 = Math.log10(input);
        if (Double.isNaN(log10)) {
            throw new IllegalArgumentException();
        }
        return log10;
    }
}
