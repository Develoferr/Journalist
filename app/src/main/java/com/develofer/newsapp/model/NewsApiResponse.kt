package com.develofer.newsapp.model

import com.google.gson.annotations.SerializedName

class NewsApiResponse {
    @SerializedName("status")   val status: String? = null
    @SerializedName("code")     val code: String? = null
    @SerializedName("articles") val newsList: List<New>? = null
}