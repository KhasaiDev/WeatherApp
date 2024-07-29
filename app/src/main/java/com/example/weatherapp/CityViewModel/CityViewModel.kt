package com.example.weatherapp.CityViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.City
import com.example.weatherapp.repo.CityRepo

class CityViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = CityRepo(application)
    private val _allCities = MutableLiveData<List<City>>()
    val allCities: LiveData<List<City>> get() = _allCities

    private val _isImperial = MutableLiveData<Boolean>()
    val isImperial: LiveData<Boolean> get() = _isImperial

    init {
        loadCities()
        _isImperial.value = false // Default to metric units
    }

    private fun loadCities() {
        _allCities.postValue(repo.readCities())
    }

    fun insert(city: City) {
        repo.insert(city)
        loadCities()
    }

    fun setUnits(isImperial: Boolean) {
        _isImperial.value = isImperial
    }
}