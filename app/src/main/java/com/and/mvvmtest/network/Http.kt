package com.example.apitestproject.network

import android.text.TextUtils
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object Http {
    private var retrofit: Retrofit? = null

    //    private var baseUrl ="https://media1."
    private val maxRetryCount = 10
    private val retryDealyMillis: Long = 1000
    private var baseUrl: String? = "https://api.giphy.com/v1/gifs/"
    var giphyURL = "https://api.giphy.com/v1/gifs/search?q="
    var currentFeeling = "&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf"
    //https://api.giphy.com/v1/gifs/random?&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf&tag=cute
    //https://api.giphy.com/v1/gifs/search?q=%ED%95%98%EB%8A%98&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf&limit=5 검색 형식
    private var okHttpClient: OkHttpClient? = null
    private val TAG = Http::class.java.simpleName
    private val TIMEOUT = 15 // 일반적인 API 타임아웃 시간

    private val okHttp: OkHttpClient?
        get() {
            if (okHttpClient == null) {
                val builder = OkHttpClient().newBuilder()
                builder.connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                builder.writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                builder.readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)

                builder.addInterceptor(httpLoggingInterceptor())
                builder.addInterceptor(AddHeaderInterceptor())
                okHttpClient = builder.build()
            }
            return okHttpClient
        }


    private val retrofitService by lazy { retrofit?.create(ApiService::class.java) }
    fun getService(): ApiService? = retrofitService

//    fun getPrdPlanQnAData(url: String): Observable<GifData> = getService()
//        .requestGetQnA(url)
//        .retryWhen(RetryWithDelay(maxRetryCount, retryDealyMillis))
//        .doOnUnsubscribe { Log.d(""," PrdPlanQnAData Rx 구독 해지 완료!!") }
//        .subscribeOn(Schedulers.io())

    @Synchronized
    fun getRetrofit():Retrofit? {
        if (retrofit == null) {
            val builder = Retrofit.Builder()
            if (!TextUtils.isEmpty(baseUrl)) {
                builder.baseUrl(baseUrl)
            }

            builder.addConverterFactory(GsonConverterFactory.create())
            builder.addConverterFactory(ScalarsConverterFactory.create())
            builder.callFactory(okHttp)
            retrofit = builder.build()
        }
        return retrofit
    }
    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {

        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.e(
                "MyGitHubData :",
                message + ""
            )
        })

        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }


}