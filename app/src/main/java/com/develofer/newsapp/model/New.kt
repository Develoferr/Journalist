package com.develofer.newsapp.model

import com.google.gson.annotations.SerializedName

data class New (
    @SerializedName("title")        var title: String,
    @SerializedName("content")      var content: String?,
    @SerializedName("author")       var author: String?,
    @SerializedName("url")          var newUrl: String,
    @SerializedName("urlToImage")   var imageUrl: String
    )