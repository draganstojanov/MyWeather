package com.andraganoid.myweather.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.andraganoid.myweather.ui.current.CurrentFragment
import com.andraganoid.myweather.ui.forecast.ForecastFragment
import com.andraganoid.myweather.ui.search.SearchFragment


class WeatherAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(CurrentFragment(), ForecastFragment(), SearchFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}