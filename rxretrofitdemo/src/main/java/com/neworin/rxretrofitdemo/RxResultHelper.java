package com.neworin.rxretrofitdemo;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
public class RxResultHelper {

    public static <T> ObservableTransformer<BasicResponse<T>, T> handleResult() {
        return new ObservableTransformer<BasicResponse<T>, T>() {
            @Override
            public Observable<T> apply(Observable<BasicResponse<T>> upstream) {
                return upstream.flatMap(new Function<BasicResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> apply(BasicResponse<T> tBasicResponse) throws Exception {
                        return createData(tBasicResponse.getData());
                    }
                });
            }
        };
    }

    /**
     * 将Response转换成 T
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> observableEmitter) throws Exception {
                observableEmitter.onNext(t);
                observableEmitter.onComplete();
            }
        });
    }
}
