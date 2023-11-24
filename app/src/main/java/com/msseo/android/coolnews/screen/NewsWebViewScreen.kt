package com.msseo.android.coolnews.screen

import androidx.compose.runtime.Composable
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun NewsWebViewScreen(
    url: String
) {
    val state = rememberWebViewState(url)
    WebView(
        state = state
    )
}