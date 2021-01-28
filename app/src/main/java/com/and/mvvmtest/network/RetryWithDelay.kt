package com.example.apitestproject.network

import rx.Observable
import rx.functions.Func1
import java.util.concurrent.TimeUnit

class RetryWithDelay (
        private val maxRetries: Int,
        private val retryDelayMillis: Long
):Func1<Observable<out Throwable>, Observable<Long>> {

    private var retryCount = 0

    override fun call(attempts:Observable<out Throwable>):Observable<Long> {
        return attempts
                .flatMap(object: Func1<Throwable, Observable<Long>> {
                    override fun call(throwable:Throwable):Observable<Long> {
                        if (++retryCount < maxRetries)
                        {
                            // When this Observable calls onNext, the original
                            // Observable will be retried (i.e. re-subscribed).
                            return Observable.timer(retryDelayMillis,
                                    TimeUnit.MILLISECONDS)
                        }
                        // Max retries hit. Just pass the error along.
                        return Observable.error(throwable)
                    }
                })
    }
}