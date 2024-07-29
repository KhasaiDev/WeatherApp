package com.example.weatherapp.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.CityViewModel.CityViewModel
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDetailBinding
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CityViewModel::class.java)

        binding.chechboxImperial.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.checkboxMetrics.isChecked = false
                viewModel.setUnits(true)
            }
        }

        binding.checkboxMetrics.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.chechboxImperial.isChecked = false
                viewModel.setUnits(false)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}