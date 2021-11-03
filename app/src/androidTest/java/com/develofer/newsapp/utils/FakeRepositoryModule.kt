package com.develofer.newsapp.utils

import com.develofer.newsapp.di.RepositoryModule
import com.develofer.newsapp.model.New
import com.develofer.newsapp.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)

class FakeRepositoryModule {

    @Provides
    @Singleton
    fun providerNewsRepository() : NewsRepository =
        object : NewsRepository  {
            val news = arrayListOf(
                New("Title1", "Content1", "Author1", "Url1", "urlImage1"),
                New("Title2", "Content2", "Author2", "Url2", "urlImage2")
            )

            override suspend fun getNews(country: String): List<New> = news

            override fun getNew(title: String): New = news[0]
        }
}