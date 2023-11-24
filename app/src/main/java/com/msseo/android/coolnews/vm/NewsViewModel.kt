package com.msseo.android.coolnews.vm

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msseo.android.coolnews.NewsWebViewActivity
import com.msseo.android.coolnews.data.model.News
import com.msseo.android.coolnews.data.model.NewsResult
import com.msseo.android.coolnews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "NewsViewModel"

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _newsHeadLines: MutableStateFlow<List<News>> = MutableStateFlow(emptyList())
    val newsHeadLines: StateFlow<List<News>> = _newsHeadLines.asStateFlow()

    init {
        queryHeadline()
    }

    fun queryHeadline() {
        viewModelScope.launch {
            newsRepository.queryHeadline().collect { result ->
                when(result) {
                    is NewsResult.Success -> _newsHeadLines.emit(result.data)
                    is NewsResult.Error -> {
                        // TODO - Load news from local.
                        Log.e(TAG, "Error during query. ${result.exception}")
                    }
                    else -> {}
                }
            }
        }
    }

    fun userClickNews(context: Context, news: News) {
        context.startActivity(
            Intent(context, NewsWebViewActivity::class.java).apply {
                putExtra(NewsWebViewActivity.KEY_WEB_URL, news.url)
            }
        )

        // Update visited state.
        _newsHeadLines.update { newsList ->
            newsList.map { oldNews ->
                if(oldNews.url == news.url) {
                    oldNews.copy(hasVisited = true)
                } else {
                    oldNews
                }
            }
        }
    }
}