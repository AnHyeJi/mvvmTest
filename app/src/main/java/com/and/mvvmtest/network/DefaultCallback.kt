package com.example.apitestproject.network


import com.daou.common.network.exception.ErrorMessage
import retrofit2.Call
import retrofit2.Response

//    private LoadingDialog dialog = null;

abstract class DefaultCallback<T> : CallbackAdapter<T>() {

    override fun onStartRequest() {
        //        showDialog();
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            if (response.body() != null) {
                onResponse(response.body()!!)
            } else {
                onError(call, response, ErrorMessage.makeNullBodyException(response))
            }
        } else {
            onError(call, response, ErrorMessage.makeUnsuccessfulException(response))
        }

    }

    override fun onFailure(call: Call<T>, t: Throwable) {

        onError(call, null, t)
    }

    override fun onCancel() {
        super.onCancel()
    }

    abstract fun onError(call: Call<T>, response: Response<T>?, t: Throwable)

    abstract fun onResponse(response: T)

}