package com.msseo.android.coolnews.data.model

sealed interface NewsResult<out T> {
    class Success<T>(val data: T) : NewsResult<T>
    class Error(val exception: Throwable? = null, val message: String = "") : NewsResult<Nothing>
    object Loading : NewsResult<Nothing>
}