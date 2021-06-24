package com.andraganoid.myweather.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andraganoid.myweather.database.DB

@Entity(tableName = DB.QUERY_TABLE)
data class QueryModel(
    @PrimaryKey
    var query: String = "",
    val name: String? = null,
    val region: String? = null,
    val country: String? = null,
    val lon: Double? = null,
    val lat: Double? = null,
)