package com.example.apitestproject.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataFetcher<T> @JvmOverloads constructor(
    private val call: Call<T>,
    private var callback: CallbackAdapter<T>?,
    retryCount: Int = 3
) {
    private val MAX_RETRY_COUNT: Int
    private var retryCount = 0
    private var realCall: Call<T>? = null
    var isFinish = false
        private set

    internal interface NetworkChecker {
        val isNetworkAvailable: Boolean
    }

    init {
        if (retryCount > RETRY_LIMIT) {
            MAX_RETRY_COUNT = RETRY_LIMIT
        } else if (retryCount < 0) {
            MAX_RETRY_COUNT = 0
        } else {
            MAX_RETRY_COUNT = retryCount
        }
    }

    fun request() {
        realCall = call.clone()
        onStartRequest(retryCount)
        realCall!!.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {

                //onResponse block은 무조건 onInternalResponse로 보냄, body의 널체크는 DefaultCallback에서 처리함.
                if (response.isSuccessful) {
                    if (response.body() == null) {
                        if (canRetry()) {
                            retry()
                        } else {
                            onInternalResponse(call, response)
                        }
                    } else {
                        onInternalResponse(call, response)
                    }
                } else {
                    if (canRetry()) {
                        retry()
                    } else {
                        onInternalResponse(call, response)
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {

                if (canRetry()) {
                    retry()
                } else {
                    onInternalFailure(call, t)
                }
            }
        })
    }

    private fun onInternalFailure(call: Call<T>, t: Throwable) {
        if (callback != null) {
            callback!!.onFailure(call, t)
        }
        isFinish = true
    }

    private fun onInternalResponse(call: Call<T>, response: Response<T>) {
        if (callback != null) {
            callback!!.onResponse(call, response)
        }
        isFinish = true
    }

    private fun onStartRequest(retryCount: Int) {
        //initial request
        if (retryCount == 0) {
            if (callback != null) {
                callback!!.onStartRequest()
            }
        }
    }

    fun cancel() {
        if (realCall != null)
            realCall!!.cancel()
        if (callback != null) {
            callback!!.onCancel()
            callback = null
        }
    }

    private fun canRetry(): Boolean {
        return retryCount < MAX_RETRY_COUNT
    }

    private fun retry() {
        retryCount++
        request()
    }

    fun getCall(): Call<T>? {
        return realCall
    }

    companion object {

        private val TAG = "DataFetcher"
        private val RETRY_LIMIT = 3
    }
}
