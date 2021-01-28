package com.example.apitestproject.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchGifData {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("bitly_gif_url")
    @Expose
    var bitly_gif_url: String? = null


    @SerializedName("bitly_url")
    @Expose
    var bitly_url: String? = null

    @SerializedName("embed_url")
    @Expose
    var embed_url: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("source")
    @Expose
    var source: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("rating")
    @Expose
    var rating: String? = null

    @SerializedName("content_url")
    @Expose
    var content_url: String? = null

    @SerializedName("source_tld")
    @Expose
    var source_tld: String? = null

    @SerializedName("source_post_url")
    @Expose
    var source_post_url: String? = null

    @SerializedName("is_sticker")
    @Expose
    var is_sticker: String? = null

    @SerializedName("import_datetime")
    @Expose
    var import_datetime: String? = null

    @SerializedName("trending_datetime")
    @Expose
    var trending_datetime: String? = null

    @SerializedName("images")
    @Expose
    var images: ORIGINAL? = null



    inner class data{

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

    inner class ORIGINAL{

        @SerializedName("original")
        @Expose
        var original: data? = null

    }

}