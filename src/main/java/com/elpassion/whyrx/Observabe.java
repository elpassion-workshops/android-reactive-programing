package com.elpassion.whyrx;

import java.util.concurrent.Executor;

public class Observabe<T> {

    private OnSubscribeAction<T> onSubscribeAction;

    private Observabe(OnSubscribeAction<T> onSubscribeAction) {
        this.onSubscribeAction = onSubscribeAction;
    }

    public void subscribe(Callback<T> onSuccess, Callback<Throwable> onError) {
        onSubscribeAction.onSubscribe(onSuccess, onError);
    }

    static <T> Observabe<T> just(T input) {
        return new Observabe<>(new SynchnousOnSubscribeAction<>(() -> input));
    }

    <R> Observabe<R> map(Function<T, R> function) {
        return new Observabe<>(new MappingOnSubscribeAction<>(onSubscribeAction, function));
    }

    Observabe<T> subscribeOn(Executor executor) {
        return new Observabe<>(new AsynchronousOnSubscribeAction<>(onSubscribeAction, executor));
    }

    interface OnSubscribeAction<T> {
        void onSubscribe(Callback<T> onSuccess, Callback<Throwable> onError);
    }

    private static class SynchnousOnSubscribeAction<T> implements OnSubscribeAction<T> {

        private final Provider<T> provider;

        SynchnousOnSubscribeAction(Provider<T> provider) {
            this.provider = provider;
        }

        @Override
        public void onSubscribe(Callback<T> onSuccess, Callback<Throwable> onError) {
            try {
                onSuccess.call(provider.provide());
            } catch (Exception e) {
                onError.call(e);
            }
        }
    }

    private static class MappingOnSubscribeAction<T, R> implements OnSubscribeAction<R> {

        private final OnSubscribeAction<T> onSubscribeAction;
        private final Function<T, R> function;

        MappingOnSubscribeAction(OnSubscribeAction<T> onSubscribeAction, Function<T, R> function) {
            this.onSubscribeAction = onSubscribeAction;
            this.function = function;
        }

        @Override
        public void onSubscribe(Callback<R> onSuccess, Callback<Throwable> onError) {
            onSubscribeAction.onSubscribe(value -> onSuccess.call(function.apply(value)), onError);
        }
    }

    private static class AsynchronousOnSubscribeAction<T> implements OnSubscribeAction<T> {

        private final OnSubscribeAction<T> onSubscribeAction;
        private final Executor executor;

        AsynchronousOnSubscribeAction(OnSubscribeAction<T> onSubscribeAction, Executor executor) {
            this.onSubscribeAction = onSubscribeAction;
            this.executor = executor;
        }

        @Override
        public void onSubscribe(Callback<T> onSuccess, Callback<Throwable> onError) {
            executor.execute(() -> onSubscribeAction.onSubscribe(onSuccess, onError));
        }
    }
}
