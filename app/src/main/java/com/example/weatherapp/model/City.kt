package com.example.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class City(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo (name = "city_name") val city_name: String,
    @ColumnInfo(name = "temp") val temp: Double,
    @ColumnInfo (name = "temp_min") val temp_min: Double,
    @ColumnInfo (name = "temp_max") val temp_max: Double,
    @ColumnInfo (name = "pressure") val pressure: Int,
    @ColumnInfo (name = "humidity") val humidity: Int,
    @ColumnInfo (name = "wind_speed") val wind_speed: Double,
    @ColumnInfo (name = "sunrise") val sunrise: Long,
    @ColumnInfo (name = "sunset") val sunset: Long
)