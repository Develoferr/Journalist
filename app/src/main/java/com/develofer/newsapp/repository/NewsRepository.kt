package com.develofer.newsapp.repository

import com.develofer.newsapp.model.New
import com.develofer.newsapp.provider.NewsProvider
import javax.inject.Inject

interface NewsRepository {
    suspend fun getNews(country: String): List<New>
    fun getNew(title: String): New
}

class NewsRepositoryImp @Inject constructor(
    private val newsProvider: NewsProvider
): NewsRepository {

    private var news: List<New> = emptyList()

    override suspend fun getNews(country: String): List<New> {
        val apiResponse = newsProvider.topHeadLines(country).body()
        if (apiResponse?.status == "error") {
            when (apiResponse.code) {
                "apiKeyMissing" -> throw MissingApiKeyException()
                "apiKeyInvalid" -> throw ApiKeyInvalidException()
                else -> throw Exception()
            }
        }
        news = apiResponse?.newsList ?: emptyList()
        return news
    }

    override fun getNew(title: String): New =
        news.first { it.title == title }

}

class MissingApiKeyException : java.lang.Exception()
class ApiKeyInvalidException : java.lang.Exception()