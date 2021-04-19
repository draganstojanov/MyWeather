package com.andraganoid.myweather.model

import com.andraganoid.myweather.util.temperatureFormatter
import com.google.gson.annotations.SerializedName

data class HourItem(

	@field:SerializedName("will_it_rain")
	val willItRain: Int? = null,

	@field:SerializedName("feelslike_c")
	val feelslikeC: Double? = null,

	@field:SerializedName("uv")
	val uv: Double? = null,

	@field:SerializedName("wind_degree")
	val windDegree: Int? = null,

	@field:SerializedName("dewpoint_c")
	val dewpointC: Double? = null,

	@field:SerializedName("windchill_c")
	val windchillC: Double? = null,

	@field:SerializedName("is_day")
	val isDay: Int? = null,

	@field:SerializedName("heatindex_c")
	val heatindexC: Double? = null,

	@field:SerializedName("wind_dir")
	val windDir: String? = null,

	@field:SerializedName("temp_c")
	val tempC: Double? = null,

	@field:SerializedName("chance_of_rain")
	val chanceOfRain: String? = null,

	@field:SerializedName("gust_kph")
	val gustKph: Double? = null,

	@field:SerializedName("precip_mm")
	val precipMm: Double? = null,

	@field:SerializedName("cloud")
	val cloud: Int? = null,

	@field:SerializedName("wind_kph")
	val windKph: Double? = null,

	@field:SerializedName("condition")
	val condition: Condition? = null,

	@field:SerializedName("will_it_snow")
	val willItSnow: Int? = null,

	@field:SerializedName("vis_km")
	val visKm: Double? = null,

	@field:SerializedName("time_epoch")
	val timeEpoch: Int? = null,

	@field:SerializedName("humidity")
	val humidity: Int? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("chance_of_snow")
	val chanceOfSnow: String? = null,

	@field:SerializedName("pressure_mb")
	val pressureMb: Double? = null
) {
    val formattedTemp: String
        get() = temperatureFormatter(tempC!!)

}