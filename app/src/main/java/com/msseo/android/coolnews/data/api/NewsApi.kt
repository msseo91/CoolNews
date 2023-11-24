package com.msseo.android.coolnews.data.api

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.msseo.android.coolnews.data.model.NewsApiResult
import com.msseo.android.coolnews.data.model.RawNews
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.Type

private const val API_KEY = "59fc5f055b7b4bfbab4c82f6f317adbf"
private const val NEWS_URL = "https://newsapi.org"

interface NewsApi {

    @GET("v2/top-headlines?country=kr")
    suspend fun queryHeadline(@Query("apiKey") apiKey: String = API_KEY): NewsApiResult
}

object NewsApiBuilder {
    private val newsType: Type = object: TypeToken<List<RawNews>>(){}.type
    private val newsGsonFactory: GsonConverterFactory =
        GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeAdapter(NewsApiResult::class.java, NewsDeserializer())
                .create()
        )

    fun build(): NewsApi =
        Retrofit.Builder()
            .baseUrl(NEWS_URL)
            .addConverterFactory(newsGsonFactory)
            .also {
                it.client(
                    OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
                )
            }
            .build()
            .create(NewsApi::class.java)

}