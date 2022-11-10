package com.andraganoid.myweather.search.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.SearchFragmentBinding
import com.andraganoid.myweather.search.ui.adapter.SavedAdapter
import com.andraganoid.myweather.util.actionSnackbar
import com.andraganoid.myweather.util.hideKeyboard
import com.andraganoid.myweather.util.logA
import com.andraganoid.myweather.util.longSnackbar
import com.andraganoid.myweather.weather.viewModel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment) {

    private val viewModel: WeatherViewModel by activityViewModels()
    private val binding: SearchFragmentBinding by viewBinding()
    private lateinit var savedAdapter: SavedAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        binding.getLocationBtn.setOnClickListener { getLocation() }
        savedAdapter = SavedAdapter(viewModel)
        binding.savedRecView.adapter = savedAdapter
        binding.locationNameBtn.setOnClickListener {
            getWeather()
            hideKeyboard(binding.root)
        }

        viewModel.getSavedQueries().observe(viewLifecycleOwner) { savedList ->
            savedAdapter.submitList(savedList)
        }

        viewModel.getLocation.observe(viewLifecycleOwner) {
            getLocation()
        }
    }

    private fun getWeather() {
        val search = binding.locationName.text.toString()
        if (search.isNotEmpty()) {
            viewModel.getForecast(search)
        }
    }

    private fun getLocation() {
        viewModel.canRepeatLastCall = false
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLocationData()
        } else requestPermission()
    }

    private fun requestPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            binding.root.actionSnackbar("Location access required") { requestPermissionLaunch() }
        } else {
            binding.root.actionSnackbar("Location access not available") { requestPermissionLaunch() }
        }
    }

    private fun requestPermissionLaunch() {
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