package com.andraganoid.myweather.model

import com.andraganoid.myweather.util.decimalFormatter
import com.andraganoid.myweather.util.temperatureFormatter
import com.google.gson.annotations.SerializedName

data class Day(

	@field:SerializedName("avgvis_km")
	val avgvisKm: Double? = null,

	@field:SerializedName("uv")
	val uv: Double? = null,

	@field:SerializedName("avgtemp_c")
	val avgtempC: Double? = null,

	@field:SerializedName("daily_chance_of_snow")
	val dailyChanceOfSnow: String? = null,

	@field:SerializedName("maxtemp_c")
	val maxtempC: Double? = null,

	@field:SerializedName("mintemp_c")
	val mintempC: Double? = null,

	@field:SerializedName("daily_will_it_rain")
	val dailyWillItRain: Int? = null,

	@field:SerializedName("avghumidity")
	val avghumidity: Double? = null,

	@field:SerializedName("condition")
	val condition: Condition? = null,

	@field:SerializedName("maxwind_kph")
	val maxwindKph: Double? = null,

	@field:SerializedName("daily_chance_of_rain")
	val dailyChanceOfRain: String? = null,

	@field:SerializedName("totalprecip_mm")
	val totalprecipMm: Double? = null,

	@field:SerializedName("daily_will_it_snow")
	val dailyWillItSnow: Int? = null
) {
    val formattedTemp: String
        get() = "${temperatureFormatter(mintempC!!)}-${temperatureFormatter(maxtempC!!)}"

    val formattedWind: String
        get() = decimalFormatter(maxwindKph!!)

    val formattedHumidity: String
        get() = decimalFormatter(avghumidity!!)

	val formattedTotalPrec: String
		get() = decimalFormatter(totalprecipMm!!)
}