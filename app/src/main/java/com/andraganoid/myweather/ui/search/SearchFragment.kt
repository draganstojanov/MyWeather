package com.andraganoid.myweather.ui.search

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.SearchFragmentBinding
import com.andraganoid.myweather.ui.WeatherViewModel
import com.andraganoid.myweather.util.actionSnackbar
import com.andraganoid.myweather.util.logA
import com.andraganoid.myweather.util.longSnackbar
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: SearchFragmentBinding
    private lateinit var savedAdapter: SavedAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        binding.getLocationBtn.setOnClickListener { getLocation() }
        savedAdapter = SavedAdapter(viewModel)
        binding.savedRecView.adapter = savedAdapter
        binding.locationNameBtn.setOnClickListener {
            getWeather()
        }

        viewModel.getSavedQuerys().observe(viewLifecycleOwner, { savedList ->
            savedAdapter.savedList = savedList
        })

        viewModel.getLocation.observe(viewLifecycleOwner, {
            getLocation()
        })
    }

    private fun getWeather() {
        val search = binding.locationName.text.toString()
        if (search.isNotEmpty()) {
            viewModel.getForecast(search)
        }
    }


    private fun getLocation() {
        viewModel.canRepeatLastCall = false

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocationData()
        } else requestPermission()
    }


    private fun requestPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            binding.root.actionSnackbar("Location access required") { launch() }
        } else {
            binding.root.actionSnackbar("Location access not available") { launch() }
        }
    }

    private fun launch() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getLocationData()
                binding.root.actionSnackbar("Permission granted") { getLocationData() }
            } else {
                binding.root.longSnackbar("Permission denied")
            }
        }


    @SuppressLint("MissingPermission")
    private fun getLocationData() {
        fusedLocationClient.locationAvailability.addOnSuccessListener { logA(it) }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    viewModel.getForecast("${location.latitude},${location.longitude}")
                }
            }.addOnFailureListener { exc ->
                binding.root.actionSnackbar(exc.message) {}
            }.addOnCanceledListener {
                binding.root.longSnackbar("Cancelled")
            }
    }
}