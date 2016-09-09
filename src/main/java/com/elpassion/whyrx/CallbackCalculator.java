package com.elpassion.whyrx;

import java.util.List;

public class CallbackCalculator {

    private final Calculator calculator;

    public CallbackCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    void generateAll(Integer limit, Callback<List<Integer>> onSuccess) {
        onSuccess.call(calculator.generateAll(limit));
    }

    void filterPrimes(List<Integer> input, Callback<List<Integer>> onSuccess) {
        onSuccess.call(calculator.filterPrimes(input));
    }

    void square(Integer base, Callback<Integer> onSuccess) {
        onSuccess.call(calculator.square(base));
    }

    void sum(List<Integer> list, Callback<Integer> onSuccess) {
        onSuccess.call(calculator.sum(list));
    }
}
