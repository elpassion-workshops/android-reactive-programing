package com.elpassion.whyrx;

import rx.Observable;

import java.util.List;

import static rx.Observable.just;

public class RxCalculator {

    private final Calculator calculator;

    public RxCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    Observable<List<Integer>> generateAll(Integer limit) {
        return just(calculator.generateAll(limit));
    }

    Observable<List<Integer>> filterPrimes(List<Integer> input) {
        return just(calculator.filterPrimes(input));
    }

    Observable<Integer> square(Integer base) {
        return just(calculator.square(base));
    }

    Observable<Integer> sum(List<Integer> list) {
        return just(calculator.sum(list));
    }
}
