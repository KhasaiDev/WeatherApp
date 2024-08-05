package com.example.weatherapp.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.CityViewModel.CityViewModel
import com.example.weatherapp.R
import com.example.weatherapp.adapter.CityAdapter
import com.example.weatherapp.database.CityDataBaseHelper
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_CITY_NAME
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_HUMIDITY
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_ID
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_MAX_TEMP
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_MIN_TEMP
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_PRESSURE
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_SUNRISE
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_SUNSET
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_TEMP
import com.example.weatherapp.database.CityDataBaseHelper.Companion.COLUMN_WIND
import com.example.weatherapp.database.CityDataBaseHelper.Companion.TABLE_USER
import com.example.weatherapp.databinding.FragmentDetailBinding
import com.example.weatherapp.model.City

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var cityAdapter: CityAdapter
    private lateinit var viewModel: CityViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
        cityAdapter = CityAdapter()

        binding.cityRecyclerView.adapter = cityAdapter
        binding.cityRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allCities.observe(viewLifecycleOwner) { cities -> cityAdapter.setCities(cities) }
        viewModel.isImperial.observe(viewLifecycleOwner) { isImperial -> cityAdapter.setUnits(isImperial) }
        return binding.root
    }


    //OnViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //paso de informacion para el nombre de la ciudad
        val cityName = arguments?.getString("cityName")
        binding.cityDetailName.text = cityName

        //Habilitamos el LongClick y llamamos al Dialogo flotante
        binding.cityDetailName.setOnLongClickListener {
            showEditCityDialog()
            true
        }

        cityAdapter = CityAdapter()
        binding.cityRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cityAdapter
        }
        viewModel.allCities.observe(viewLifecycleOwner){ cities ->
            cities?.let { (binding.cityRecyclerView.adapter as CityAdapter).setCities(it) }
        }

    }


    //Dialogo Flotante para el cambio de nombre de la ciudad
    private fun showEditCityDialog() {
        // Inflar el layout del diálogo
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_pop, null)
        val editText = dialogView.findViewById<EditText>(R.id.editCityName)

        // Crear el diálogo
        AlertDialog.Builder(requireContext())
            .setTitle("Edit City Name")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                // Actualizar el TextView con el nuevo nombre
                val newCityName = editText.text.toString()
                if (newCityName.isNotEmpty()) {
                    binding.cityDetailName.text = newCityName
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


    //OnDestroyView
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
