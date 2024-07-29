package com.example.weatherapp.view.fragments

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.database.CityDataBaseHelper
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_CITY_NAME
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_HUMIDITY
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_MAX_TEMP
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_MIN_TEMP
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_PRESSURE
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_SUNRISE
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_SUNSET
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_TEMP
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_WIND
import com.example.weatherapp.database.CityDataBaseHelper.Companion.TABLE_USER
import com.example.weatherapp.databinding.FragmentDetailBinding
import com.example.weatherapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var cityDatabaseHelper: CityDataBaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityDatabaseHelper = CityDataBaseHelper(requireContext())

        binding.cityButton.setOnClickListener {
            val cityName = binding.cityName.text.toString()
            if (cityName.isNotEmpty()){
                val bundle = Bundle()
                bundle.putString("cityName", cityName)
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }else{
                Toast.makeText(context, "Ingresa un nombre de ciudad de origen",  Toast.LENGTH_SHORT).show()
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
