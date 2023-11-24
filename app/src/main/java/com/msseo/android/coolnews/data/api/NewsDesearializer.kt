package com.msseo.android.coolnews.data.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.msseo.android.coolnews.data.model.NewsApiResult
import com.msseo.android.coolnews.data.model.RawNews
import java.lang.reflect.Type

class NewsDeserializer : JsonDeserializer<NewsApiResult> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): NewsApiResult {
        val articles = json.asJsonObject.getAsJsonArray("articles")

        return NewsApiResult(
            total = json.asJsonObject.get("totalResults").asInt,
            rawNewsList = articles.map { it.asJsonObject }
                .map { article ->
                    RawNews(
                        author = article.getOrEmptyString("author"),
                        title = article.getOrEmptyString("title"),
                        description = article.getOrEmptyString("description"),
                        url = article.getOrEmptyString("url"),
                        urlToImage = article.getOrEmptyString("urlToImage"),
                        publishedAt = article.getOrEmptyString("publishedAt")
                    )
                }
        )
    }

    private fun JsonObject.getOrEmptyString(key: String): String =
        if(has(key)) {
            get(key).let { if(it.isJsonNull) "" else it.asString }
        } else {
            ""
        }
}