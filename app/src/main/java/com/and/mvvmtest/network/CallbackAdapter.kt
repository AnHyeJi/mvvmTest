package com.example.apitestproject.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class CallbackAdapter<T> : Callback<T> {

    open fun onStartRequest() {}

    override fun onResponse(call: Call<T>, response: Response<T>) {}

    override fun onFailure(call: Call<T>, t: Throwable) {}

    open fun onCancel() {}
}
