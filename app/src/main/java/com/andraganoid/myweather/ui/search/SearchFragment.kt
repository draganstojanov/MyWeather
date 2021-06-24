package com.andraganoid.myweather.ui.search

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.SearchFragmentBinding
import com.andraganoid.myweather.ui.WeatherViewModel
import com.andraganoid.myweather.util.logA
import com.eazypermissions.common.model.PermissionResult
import com.eazypermissions.dsl.extension.requestPermissions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
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
        requestPermissions(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) {
            requestCode = 4
            resultCallback = {
                when (this) {
                    is PermissionResult.PermissionGranted -> {
                        getLocationData()
                    }
                    is PermissionResult.PermissionDenied -> {
                        Snackbar.make(binding.root, "Location permission denied. Try again.", Snackbar.LENGTH_LONG).show()
                    }
                    is PermissionResult.PermissionDeniedPermanently -> {
                        Snackbar.make(binding.root, "Location permission denied. Try manually at device settings.", Snackbar.LENGTH_LONG).show()
                    }
                    is PermissionResult.ShowRational -> {
                        AlertDialog.Builder(requireActivity())
                            .setTitle("Location permission")
                            .setMessage("This app requires access to get Device location.")
                            .setPositiveButton("Ask me") { _, _ -> getLocation() }
                            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                            .show()
                    }
                }
            }
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
                Snackbar.make(binding.root, exc.localizedMessage!!, Snackbar.LENGTH_LONG).show()
            }.addOnCanceledListener {
                Snackbar.make(binding.root, "Cancelled", Snackbar.LENGTH_LONG).show()
            }
    }
}