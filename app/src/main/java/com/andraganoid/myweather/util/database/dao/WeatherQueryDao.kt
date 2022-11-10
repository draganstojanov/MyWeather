package com.andraganoid.myweather.util.database.dao

import androidx.room.*
import com.andraganoid.myweather.util.database.model.QueryModel
import com.andraganoid.myweather.util.database.constant.QUERY_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherQueryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuery(query: QueryModel)

    @Delete
    suspend fun deleteQuery(query: QueryModel)

    @Query("SELECT * FROM $QUERY_TABLE")
    fun allQueries(): Flow<List<QueryModel>>

}