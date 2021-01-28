package com.example.apitestproject.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AddHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest: Request
        newRequest = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "DefaultConst().getAuthToken()")
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private val TAG = AddHeaderInterceptor::class.java.simpleName
    }
}