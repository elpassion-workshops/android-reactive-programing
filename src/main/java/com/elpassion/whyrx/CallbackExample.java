package com.elpassion.whyrx;

public class CallbackExample {

    Double calculate(Double input) {
        return log10(log10(input));
    }

    Double log10(Double input){
        return Math.log10(input);
    }
}
