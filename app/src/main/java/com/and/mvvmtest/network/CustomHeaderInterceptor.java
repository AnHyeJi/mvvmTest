package com.and.mvvmtest.network;

import android.webkit.CookieManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        String cookieStr = "";
        cookieStr = CookieManager.getInstance().getCookie(".galleria.co.kr");

        Request request = chain.request().newBuilder()
                .addHeader("User-Agent", "GALLERIA_MOBILE_ANDROID&scheme=galleriaapp&ver=")
                .addHeader("Cookie", "cookieStr")
                .build();

        return chain.proceed(request);
    }
}