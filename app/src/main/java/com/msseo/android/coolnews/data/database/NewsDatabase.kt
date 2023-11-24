package com.msseo.android.coolnews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.msseo.android.coolnews.data.model.News

@Database(entities = [News::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsRoom(): NewsRoom
}