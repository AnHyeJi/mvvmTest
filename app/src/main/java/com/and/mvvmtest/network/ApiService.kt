package com.example.apitestproject.network

import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*
import rx.Observable

interface ApiService {

    /**
     *
     *
     */
    @GET("random?&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf")
    fun getGif(): Call<JsonObject>
    /**
     *
     *
     */
    @GET("search?q=%ED%95%98%EB%8A%98&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf")
    fun getSearchData(): Observable<JsonObject>

    /**
     * 검색
     *
     */
    @GET
    fun getSearchData(@Url fullUrl: String): Observable<JsonObject>

    @GET("json/product_new/product_view.json")
    fun requestGetPlanPrd(
        @Query("search?q=") search: String): Observable<JsonObject>
    /**
     *
     *
     */
    @GET("search?q=%ED%95%98%EB%8A%98&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf")
    fun getSearchGif(): Call<JsonObject>
    /**
     *
     *
     */
    @GET("search?q=%ED%95%98%EB%8A%98&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf&limit=30")
    fun requestSearch(): Call<JsonObject>
//https://api.giphy.com/v1/gifs/search?q=%ED%95%98%EB%8A%98&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf&limit=5
//https://api.giphy.com/v1/gifs/search?q=%ED%95%98%EB%8A%98&api_key=R2ReJYiecdh5wSe2LfvaK8tEPIFAdMLf&limit=5
}