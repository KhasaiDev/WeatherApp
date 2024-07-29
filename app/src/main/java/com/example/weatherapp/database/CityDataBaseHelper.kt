package com.example.weatherapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.weatherapp.model.City

class CityDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableCity = ("CREATE TABLE " + TABLE_USER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CITY_NAME + " TEXT,"
                + COLUMN_TEMP + " REAL,"
                + COLUMN_MIN_TEMP + " REAL,"
                + COLUMN_MAX_TEMP + " REAL,"
                + COLUMN_PRESSURE + " INTEGER,"
                + COLUMN_HUMIDITY + " INTEGER,"
                + COLUMN_WIND + " REAL,"
                + COLUMN_SUNRISE + " INTEGER,"
                + COLUMN_SUNSET + " INTEGER" + ")")
        db.execSQL(createTableCity)

        // Insert initial data
        insertInitialData(db)
    }

    private fun insertInitialData(db: SQLiteDatabase) {
        val initialCities = listOf(
            City(0, "Santiago", 20.0, 15.0, 25.0, 1013, 60, 5.5, 1625587200L, 1625630400L),
            City(0, "Buenos Aires", 18.0, 12.0, 22.0, 1012, 70, 3.2, 1625583600L, 1625626800L),
            City(0, "Lima", 22.0, 16.0, 26.0, 1011, 80, 4.0, 1625580000L, 1625623200L)
        )

        for (city in initialCities) {
            val values = ContentValues().apply {
                put(COLUMN_CITY_NAME, city.city_name)
                put(COLUMN_TEMP, city.temp)
                put(COLUMN_MIN_TEMP, city.temp_min)
                put(COLUMN_MAX_TEMP, city.temp_max)
                put(COLUMN_PRESSURE, city.pressure)
                put(COLUMN_HUMIDITY, city.humidity)
                put(COLUMN_WIND, city.wind_speed)
                put(COLUMN_SUNRISE, city.sunrise)
                put(COLUMN_SUNSET, city.sunset)
            }
            db.insert(TABLE_USER, null, values)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }
    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "city_database"
        const val TABLE_USER = "city"
        const val COLUMN_ID = "id"
        const val COLUMN_CITY_NAME = "city_name"
        const val COLUMN_TEMP = "temp"
        const val COLUMN_MIN_TEMP = "temp_min"
        const val COLUMN_MAX_TEMP = "temp_max"
        const val COLUMN_PRESSURE = "pressure"
        const val COLUMN_HUMIDITY = "humidity"
        const val COLUMN_WIND = "wind_speed"
        const val COLUMN_SUNRISE = "sunrise"
        const val COLUMN_SUNSET = "sunset"
    }
}
