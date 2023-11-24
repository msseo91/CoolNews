package com.msseo.android.coolnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.msseo.android.coolnews.screen.NewsWebViewScreen

class NewsWebViewActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val webUrl = intent.getStringExtra(KEY_WEB_URL) ?: return
        setContent { 
            NewsWebViewScreen(url = webUrl)
        }
    }
    
    companion object {
        const val KEY_WEB_URL = "web_url"
    }
}