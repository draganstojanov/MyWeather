package com.andraganoid.myweather.ui

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.andraganoid.myweather.R
import com.andraganoid.myweather.databinding.WeatherFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.weather_fragment) {

    private val viewModel: WeatherViewModel by activityViewModels()
    private lateinit var tabTitle: List<String>
    private val binding: WeatherFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        tabTitle = arrayListOf(
            getString(R.string.now).uppercase(Locale.getDefault()),
            getString(R.string.forecast).uppercase(Locale.getDefault()),
            getString(R.string.search).uppercase(Locale.getDefault())
        )
        binding.viewPager.apply {
            adapter = WeatherAdapter(this@WeatherFragment)
            offscreenPageLimit = tabTitle.size
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
        viewModel.showFragment.observe(viewLifecycleOwner, {
            binding.viewPager.currentItem = it
        })
    }

}