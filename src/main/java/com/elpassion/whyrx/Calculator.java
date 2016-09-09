package com.elpassion.whyrx;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Calculator {

    List<Integer> generateAll(Integer limit) {
        return IntStream.range(1, limit).boxed().collect(toList());
    }

    List<Integer> filterPrimes(List<Integer> input) {
        return input.stream().filter(this::isPrime).collect(toList());
    }

    private boolean isPrime(Integer number) {
        return IntStream.range(2, number).filter((i) -> number % i == 0).limit(1).count() == 0;
    }

    Integer square(Integer base) {
        return base * base;
    }

    Integer sum(@NotNull List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }
}
