package com.example.apitestproject.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchGifData1 {
//     @JvmField
//     var isORIGINAL: List<ORIGINAL>? = null
//
    @SerializedName("image")
    @Expose
    var imgages: List<ORIGINAL>? = null
//
    @SerializedName("orignal")
    @Expose
    var orignal: ORIGINAL? = null


    inner class ORIGINAL{
        @SerializedName("height")
        @Expose
        var height: String? = null

        @SerializedName("width")
        @Expose
        var width: String? = null

        @SerializedName("size")
        @Expose
        var size: String? = null

        @SerializedName("url")
        @Expose
        var url: String? = null

        @SerializedName("mp4_size")
        @Expose
        var mp4_size: String? = null

        @SerializedName("mp4")
        @Expose
        var mp4: String? = null

        @SerializedName("webp_size")
        @Expose
        var webp_size: String? = null

        @SerializedName("webp")
        @Expose
        var webp: String? = null

        @SerializedName("frames")
        @Expose
        var frames: String? = null

        @SerializedName("hash")
        @Expose
        var hash: String? = null
    }

}