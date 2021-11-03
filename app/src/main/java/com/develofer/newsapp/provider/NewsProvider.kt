package com.develofer.newsapp.provider

import com.develofer.newsapp.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "48a3e87062ca4e4db404c445b1efe0f8"

interface NewsProvider {

    @GET("top-headlines?apiKey=$API_KEY")
    suspend fun topHeadLines (@Query("country") country: String) : Response<NewsApiResponse>
}