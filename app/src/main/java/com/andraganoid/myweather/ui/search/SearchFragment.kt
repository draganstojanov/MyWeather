package com.andraganoid.myweather.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.SearchFragmentBinding
import com.andraganoid.myweather.ui.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()

    private lateinit var binding: SearchFragmentBinding

    lateinit var savedAdapter: SavedAdapter
    lateinit var searchAdapter: SearchAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        savedAdapter = SavedAdapter(viewModel)
        binding.savedRecView.adapter = savedAdapter

        searchAdapter = SearchAdapter(viewModel)
        binding.locationRecView.adapter = searchAdapter

        binding.locationNameBtn.setOnClickListener {
            getWeather()
        }

        viewModel.getSavedQuerys().observe(viewLifecycleOwner, { savedList ->
            savedAdapter.savedList = savedList
        })
    }

    private fun getWeather() {
        val search = binding.locationName.text.toString()
        if (search.isNotEmpty()) {
            viewModel.getForecast(search)
        }
    }

}