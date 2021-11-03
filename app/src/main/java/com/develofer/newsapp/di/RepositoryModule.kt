package com.develofer.newsapp.di

import com.develofer.newsapp.provider.NewsProvider
import com.develofer.newsapp.repository.NewsRepository
import com.develofer.newsapp.repository.NewsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providersNewsRepository(provider: NewsProvider): NewsRepository =
        NewsRepositoryImp(provider)
}