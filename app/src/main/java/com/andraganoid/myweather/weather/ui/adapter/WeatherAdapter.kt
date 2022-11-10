package com.andraganoid.myweather.weather.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.andraganoid.myweather.current.ui.fragment.CurrentFragment
import com.andraganoid.myweather.forecast.ui.fragment.ForecastFragment
import com.andraganoid.myweather.search.ui.fragment.SearchFragment


class WeatherAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(CurrentFragment(), ForecastFragment(), SearchFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}