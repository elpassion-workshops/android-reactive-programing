package com.elpassion.whyrx;

public class Observabe<T> {
    private final Provider<T> provider;

    public Observabe(Provider<T> provider) {
        this.provider = provider;
    }

    public void subscribe(Callback<T> onSuccess, Callback<Throwable> onError) {
        try {
            onSuccess.call(provider.provide());
        } catch (Exception e) {
            onError.call(e);
        }
    }

    public static <T> Observabe<T> just(T input) {
        return new Observabe<>(() -> input);
    }

    public <R> Observabe<R> map(Function<T, R> function) {
        return new Observabe<>(() -> function.apply(provider.provide()));
    }
}
