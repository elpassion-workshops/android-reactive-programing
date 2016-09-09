package com.elpassion.whyrx;

import java.util.List;
import java.util.stream.Collectors;

public class SumOfPrimeSquaredCalculator {

    private final Calculator calculator;

    public SumOfPrimeSquaredCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public int calculateSynchronously(int limit) {
        List<Integer> range = calculator.generateAll(limit);
        List<Integer> primes = calculator.filterPrimes(range);
        List<Integer> primesSquared = primes.stream().map(calculator::square).collect(Collectors.toList());
        return calculator.sum(primesSquared);
    }
}
