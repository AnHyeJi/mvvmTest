package com.example.apitestproject.network

import android.util.Log
import android.webkit.CookieManager
import androidx.databinding.library.BuildConfig
import com.and.mvvmtest.network.UserAgentManager
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private val maxRetryCount = 10
    private val retryDealyMillis: Long = 1000

    companion object {
        private val retrofitClient: RetrofitClient = RetrofitClient()

        fun getInstance(): RetrofitClient {
            return retrofitClient
        }
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https:"+"//api.giphy.com/v1/gifs/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()
    }
    /**
     * 통신 로그 확인용
     */
    private fun createOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .readTimeout(1, TimeUnit.MINUTES)   // Server 대응 ( STG, TEST, MPRJ ) 1분 이상 걸릴 경우 늘려야 함.
            .connectTimeout(1, TimeUnit.MINUTES)    // Server 대응 ( STG, TEST, MPRJ )
            .build()
    }

    private val interceptorChain = Interceptor {
        val cookie = getCookies()
        val builder = it.request().newBuilder()
            .header("Cookie", cookie)
            .header("User-Agent", UserAgentManager.getInstance().userAgent)
            .header("If-None-Match", "\"vENFxg\"")

        it.proceed(builder.build())
    }
    private val retrofitService by lazy { retrofit.create(ApiService::class.java) }
    fun getService(): ApiService = retrofitService

    fun getCookies(): String? {
        val cookieManager = CookieManager.getInstance()
        var cookies: String? = null
        try {
            cookies = cookieManager.getCookie("")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (cookies == null) cookies = ""

        // Logger.d("UTIL getCookie: " + cookies);
        return cookies
    }
    val getGifImage: Observable<JsonObject> = getService()
        .getSearchData()
        .retryWhen(RetryWithDelay(maxRetryCount, retryDealyMillis))
        .doOnUnsubscribe { Log.d(""," IntroImage Rx 구독 해지 완료!!") }
        .subscribeOn(Schedulers.io())

    fun getGifImage(url: String): Observable<JsonObject> = getService()
        .getSearchData("search?q=$url&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf")
        .retryWhen(RetryWithDelay(maxRetryCount, retryDealyMillis))
        .doOnUnsubscribe { Log.d(""," IntroImage Rx 구독 해지 완료!!") }
        .subscribeOn(Schedulers.io())

}