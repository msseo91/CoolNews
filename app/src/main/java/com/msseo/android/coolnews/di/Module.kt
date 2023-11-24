package com.msseo.android.coolnews.di

import com.msseo.android.coolnews.data.api.NewsApi
import com.msseo.android.coolnews.data.api.NewsApiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi = NewsApiBuilder.build()
}