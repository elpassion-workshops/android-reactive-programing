package com.elpassion.whyrx;

import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SumOfPrimeSquaredCalculator {

    private final Calculator calculator;
    private final CallbackCalculator callbackCalculator;
    private final RxCalculator rxCalculator;

    public SumOfPrimeSquaredCalculator(Calculator calculator, CallbackCalculator callbackCalculator, RxCalculator rxCalculator) {
        this.calculator = calculator;
        this.callbackCalculator = callbackCalculator;
        this.rxCalculator = rxCalculator;
    }

    public int calculateSynchronously(int limit) {
        List<Integer> range = calculator.generateAll(limit);
        List<Integer> primes = calculator.filterPrimes(range);
        List<Integer> primesSquared = primes.stream().map(calculator::square).collect(Collectors.toList());
        return calculator.sum(primesSquared);
    }

    public void calculateWithCallback(int limit, Callback<Integer> onSuccess) {
        callbackCalculator.generateAll(limit, (range) -> filterPrimes(onSuccess, range));
    }

    private void filterPrimes(Callback<Integer> onSuccess, List<Integer> range) {
        callbackCalculator.filterPrimes(range, (primes) -> square(primes, onSuccess));
    }

    private void square(List<Integer> primes, Callback<Integer> onSuccess) {
        List<Integer> squaredPrimes = new ArrayList<>();
        primes.forEach((prime) -> callbackCalculator.square(prime, (square) -> {
            squaredPrimes.add(square);
            if (squaredPrimes.size() == primes.size()) {
                sum(squaredPrimes, onSuccess);
            }
        }));
    }

    private void sum(List<Integer> squaredPrimes, Callback<Integer> onSuccess) {
        callbackCalculator.sum(squaredPrimes, onSuccess);
    }

    public Observable<Integer> calculateWithRx(int limit) {
        return rxCalculator.generateAll(limit)
                .flatMap(rxCalculator::filterPrimes)
                .flatMap(Observable::from)
                .flatMap(rxCalculator::square)
                .toList()
                .flatMap(rxCalculator::sum);
    }
}
