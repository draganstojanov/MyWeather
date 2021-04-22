package com.andraganoid.myweather.database

import androidx.room.*
import com.andraganoid.myweather.model.db.QueryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherQueryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuery(query: QueryModel)

    @Delete
    suspend fun deleteQuery(query: QueryModel)

    @Query("SELECT * FROM query_table")
    fun allQuerys(): Flow<List<QueryModel>>

}