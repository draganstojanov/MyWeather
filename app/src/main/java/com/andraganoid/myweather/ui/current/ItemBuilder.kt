package com.andraganoid.myweather.ui.current

import android.content.Context
import com.andraganoid.myweather.R
import com.andraganoid.myweather.model.response.Astronomy
import com.andraganoid.myweather.model.response.Current
import com.andraganoid.myweather.util.DateFormatter

class ItemBuilder(private val context: Context) {

    fun detailsList(current: Current?): ArrayList<ItemModel> {
        val detailsItem = arrayListOf<ItemModel>()
        return detailsItem.apply {
            add(ItemModel(label = context.getString(R.string.wind_dir), value = current?.windDir))
            add(ItemModel(label = context.getString(R.string.wind_speed), value = current?.windKph, unit = context.getString(R.string.kmh)))
            add(ItemModel(label = context.getString(R.string.wind_gust), value = current?.gustKph, unit = context.getString(R.string.kmh)))
            add(ItemModel(label = context.getString(R.string.pressure), value = current?.pressureMb, unit = context.getString(R.string.mbar)))
            add(ItemModel(label = context.getString(R.string.humidity), value = current?.humidity, unit = context.getString(R.string.percent)))
            add(ItemModel(label = context.getString(R.string.precipitation), value = current?.precipMm, unit = context.getString(R.string.mm)))
            add(ItemModel(label = context.getString(R.string.uv_index), value = current?.precipMm))
            add(ItemModel(label = context.getString(R.string.visibility), value = current?.visKm, unit = context.getString(R.string.km)))
            add(ItemModel(label = context.getString(R.string.clouds), value = current?.cloud, unit = context.getString(R.string.percent)))
        }
    }

    fun airList(current: Current?): java.util.ArrayList<ItemModel> {
        val airItem = arrayListOf<ItemModel>()
        return airItem.apply {
            add(ItemModel(label = context.getString(R.string.carbon_monoxide), value = current?.airQuality?.co, unit = context.getString(R.string.mgm3)))
            add(ItemModel(label = context.getString(R.string.ozone), value = current?.airQuality?.o3, unit = context.getString(R.string.mgm3)))
            add(ItemModel(label = context.getString(R.string.nitrogen_dioxide), value = current?.airQuality?.no2, unit = context.getString(R.string.mgm3)))
            add(ItemModel(label = context.getString(R.string.sulphur_dioxide), value = current?.airQuality?.so2, unit = context.getString(R.string.mgm3)))
            add(ItemModel(label = context.getString(R.string.pm2_5), value = current?.airQuality?.pm25, unit = context.getString(R.string.mgm3)))
            add(ItemModel(label = context.getString(R.string.pm10), value = current?.airQuality?.pm10, unit = context.getString(R.string.mgm3)))
        }
    }

    fun astroList(astronomy: Astronomy?): java.util.ArrayList<ItemModel> {
        val astroItem = arrayListOf<ItemModel>()
        return astroItem.apply {
            add(ItemModel(label = context.getString(R.string.sunrise), value = DateFormatter.to24hFormat(astronomy?.astro?.sunrise!!)))
            add(ItemModel(label = context.getString(R.string.moonrise), value = DateFormatter.to24hFormat(astronomy.astro.moonrise!!)))
            add(ItemModel(label = context.getString(R.string.moon_phase), value = astronomy.astro.moonPhase))
            add(ItemModel(label = context.getString(R.string.sunset), value = DateFormatter.to24hFormat(astronomy.astro.sunset!!)))
            add(ItemModel(label = context.getString(R.string.moonset), value = DateFormatter.to24hFormat(astronomy.astro.moonset!!)))
            add(ItemModel(label = context.getString(R.string.moon_illumination), value = astronomy.astro.moonIllumination))
        }
    }


}