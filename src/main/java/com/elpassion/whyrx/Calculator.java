package com.elpassion.whyrx;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Calculator {

    List<Integer> generateAll(Integer limit) {
        return IntStream.range(1, limit).boxed().collect(toList());
    }
}
