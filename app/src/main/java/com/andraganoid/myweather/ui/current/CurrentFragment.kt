package com.andraganoid.myweather.ui.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.CurrentFragmentBinding
import com.andraganoid.myweather.model.response.Astronomy
import com.andraganoid.myweather.model.response.Current
import com.andraganoid.myweather.ui.WeatherViewModel
import com.andraganoid.myweather.util.DateFormatter
import com.andraganoid.myweather.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class CurrentFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()

    @Inject
    lateinit var itemBuilder: ItemBuilder

    private lateinit var binding: CurrentFragmentBinding
    private lateinit var itemList: ArrayList<ItemModel>
    private lateinit var detailsAdapter: ItemAdapter
    private lateinit var astroAdapter: ItemAdapter
    private lateinit var airAdapter: ItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.current_fragment, container, false)
        setup()
        return binding.root
    }

    private fun setup() {

        detailsAdapter = ItemAdapter()
        binding.detailsRecView.apply {
            adapter = detailsAdapter
            itemAnimator = null
        }
        airAdapter = ItemAdapter()
        binding.airRecView.apply {
            adapter = airAdapter
            itemAnimator = null
        }
        astroAdapter = ItemAdapter()
        binding.astroRecView.apply {
            adapter = astroAdapter
            itemAnimator = null
        }

        binding.refreshBtn.setOnClickListener {
            viewModel.repeatLastCall()
        }

        viewModel.astronomyData.observe(viewLifecycleOwner, { setAstronomy(it) })
        viewModel.currentData.observe(viewLifecycleOwner, { setCurrentWeather(it) })
        viewModel.locationData.observe(viewLifecycleOwner, { binding.location = it })

    }

    private fun setCurrentWeather(current: Current?) {
        hideKeyboard(binding.root)
        viewModel._showFragment.value = 0
        binding.loading = false

        binding.current = current
        binding.weekDayToday.text = DateFormatter.todayWeekDay()
        binding.dateToday.text = DateFormatter.dateToday()

        detailsAdapter.itemList = itemBuilder.detailsList(current)
        airAdapter.itemList = itemBuilder.airList(current)
        binding.rootScrollView.isVisible = true
    }

    private fun setAstronomy(astronomy: Astronomy?) {
        astroAdapter.itemList = itemBuilder.astroList(astronomy)
        binding.astroRecView.isVisible = true
    }

}