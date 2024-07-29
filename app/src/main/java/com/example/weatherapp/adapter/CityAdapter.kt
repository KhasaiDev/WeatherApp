package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.City

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private var cityList = emptyList<City>()
    private var isImperial = false

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityNameText: TextView = itemView.findViewById(R.id.cityNameText)
        val tempText: TextView = itemView.findViewById(R.id.tempText)
        val tempMinText: TextView = itemView.findViewById(R.id.tempMinText)
        val tempMaxText: TextView = itemView.findViewById(R.id.tempMaxText)
        val pressureText: TextView = itemView.findViewById(R.id.pressureText)
        val humidityText: TextView = itemView.findViewById(R.id.humidityText)
        val windSpeedText: TextView = itemView.findViewById(R.id.windSpeedText)
        val sunriseText: TextView = itemView.findViewById(R.id.sunriseText)
        val sunsetText: TextView = itemView.findViewById(R.id.sunsetText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val currentCity = cityList[position]
        holder.cityNameText.text = currentCity.city_name
        holder.tempText.text = formatTemperature(currentCity.temp)
        holder.tempMinText.text = formatTemperature(currentCity.temp_min)
        holder.tempMaxText.text = formatTemperature(currentCity.temp_max)
        holder.pressureText.text = "${currentCity.pressure} hPa"
        holder.humidityText.text = "${currentCity.humidity}%"
        holder.windSpeedText.text = "${currentCity.wind_speed} m/s"
        holder.sunriseText.text = "${currentCity.sunrise}"
        holder.sunsetText.text = "${currentCity.sunset}"
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    fun setCities(cities: List<City>) {
        this.cityList = cities
        notifyDataSetChanged()
    }

    fun setUnits(isImperial: Boolean) {
        this.isImperial = isImperial
        notifyDataSetChanged()
    }

    private fun formatTemperature(temp: Double): String {
        return if (isImperial) {
            "${(temp * 9 / 5 + 32).toInt()}°F"
        } else {
            "${temp.toInt()}°C"
        }
    }
}
