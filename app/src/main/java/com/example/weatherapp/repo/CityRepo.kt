package com.example.weatherapp.repo

import android.content.ContentValues
import android.content.Context
import com.example.weatherapp.database.CityDataBaseHelper
import com.example.weatherapp.model.City

class CityRepo(context:Context) {
    private val dataBase = CityDataBaseHelper(context)

    fun insert(city: City): Long {
        val dataBase = dataBase.writableDatabase
        val values = ContentValues().apply {
            put(CityDataBaseHelper.COLUMN_CITY_NAME, city.city_name)
            put(CityDataBaseHelper.COLUMN_TEMP, city.temp)
            put(CityDataBaseHelper.COLUMN_MIN_TEMP, city.temp_min)
            put(CityDataBaseHelper.COLUMN_MAX_TEMP, city.temp_max)
            put(CityDataBaseHelper.COLUMN_PRESSURE, city.pressure)
            put(CityDataBaseHelper.COLUMN_HUMIDITY, city.humidity)
            put(CityDataBaseHelper.COLUMN_WIND, city.wind_speed)
            put(CityDataBaseHelper.COLUMN_SUNRISE, city.sunrise)
            put(CityDataBaseHelper.COLUMN_SUNSET, city.sunset)
        }
        val id = dataBase.insert(CityDataBaseHelper.TABLE_USER, null, values)
        city.id = id.toInt()
        return id
    }

    fun readCities(): List<City> {
        val dataBase = dataBase.readableDatabase
        val cursor = dataBase.query(CityDataBaseHelper.TABLE_USER, null, null, null, null, null, null)
        val cities = mutableListOf<City>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_ID))
                val cityName = getString(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_CITY_NAME))
                val temp = getDouble(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_TEMP))
                val tempMin = getDouble(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_MIN_TEMP))
                val tempMax = getDouble(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_MAX_TEMP))
                val pressure = getInt(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_PRESSURE))
                val humidity = getInt(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_HUMIDITY))
                val windSpeed = getDouble(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_WIND))
                val sunrise = getLong(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_SUNRISE))
                val sunset = getLong(getColumnIndexOrThrow(CityDataBaseHelper.COLUMN_SUNSET))
                val city = City(
                    id = id,
                    city_name = cityName,
                    temp = temp,
                    temp_min = tempMin,
                    temp_max = tempMax,
                    pressure = pressure,
                    humidity = humidity,
                    wind_speed = windSpeed,
                    sunrise = sunrise,
                    sunset = sunset
                )
                cities.add(city)
            }
        }
        cursor.close()
        return cities
    }

}