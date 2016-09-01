package com.elpassion.whyrx;

import java.util.concurrent.Executor;

class Observable<T> {
    private final Provider<T> provider;
    private final Executor executor;

    private Observable(Provider<T> provider) {
        this(provider, new DefaultExecutor());
    }

    private Observable(Provider<T> provider, Executor executor) {
        this.provider = provider;
        this.executor = executor;
    }

    <R> Observable<R> map(final Function<T, R> function) {
        return new Observable<R>(new Provider<R>() {
            public R provide() {
                return function.apply(provider.provide());
            }
        });
    }

    Observable<T> subscribeOn(final Executor executor) {
        return new Observable<T>(provider, executor);
    }

    public void subscribe(final Callback<T> onSuccess, final Callback<Exception> onError) {
        executor.execute(new Runnable() {
            public void run() {
                try {
                    onSuccess.call(provider.provide());
                } catch (Exception e) {
                    onError.call(e);
                }
            }
        });
    }

    static <T> Observable<T> just(final T input) {
        return new Observable<T>(new Provider<T>() {
            public T provide() {
                return input;
            }
        });
    }
}
