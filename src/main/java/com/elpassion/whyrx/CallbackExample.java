package com.elpassion.whyrx;

public class CallbackExample {

    void calculate(Double input, Callback<Double> onSuccess, Callback<Throwable> onError) {
        try {
            onSuccess.call(log10(log10(input)));
        } catch (Exception e) {
            onError.call(e);
        }
    }

    Double log10(Double input) {
        double log10 = Math.log10(input);
        if (Double.isNaN(log10)) {
            throw new IllegalArgumentException();
        }
        return log10;
    }
}
