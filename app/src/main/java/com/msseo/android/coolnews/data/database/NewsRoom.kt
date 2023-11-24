package com.msseo.android.coolnews.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msseo.android.coolnews.data.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsRoom {
    @Query("SELECT * FROM news")
    fun getAllNews(): Flow<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(vararg news: News)

    @Delete
    suspend fun deleteNews(vararg news: News)

    @Query("DELETE FROM news")
    suspend fun clear()
}