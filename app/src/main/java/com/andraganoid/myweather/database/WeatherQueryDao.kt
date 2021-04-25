package com.andraganoid.myweather.database

import androidx.room.*
import com.andraganoid.myweather.model.db.QueryModel
import com.andraganoid.myweather.util.DB
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherQueryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuery(query: QueryModel)

    @Delete
    suspend fun deleteQuery(query: QueryModel)

    @Query("SELECT * FROM ${DB.QUERY_TABLE}")
    fun allQuerys(): Flow<List<QueryModel>>

}