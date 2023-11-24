package com.msseo.android.coolnews.di

import android.content.Context
import androidx.room.Room
import com.msseo.android.coolnews.data.api.NewsApi
import com.msseo.android.coolnews.data.api.NewsApiBuilder
import com.msseo.android.coolnews.data.database.NewsDatabase
import com.msseo.android.coolnews.data.database.NewsRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi = NewsApiBuilder.build()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, "news.db").build()

    @Provides
    fun provideRoomDao(database: NewsDatabase): NewsRoom = database.newsRoom()
}