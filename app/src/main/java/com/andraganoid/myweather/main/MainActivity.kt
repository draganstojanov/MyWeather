package com.andraganoid.myweather.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andraganoid.myweather.R
import com.andraganoid.myweather.ui.WeatherFragment
import com.andraganoid.myweather.util.logA
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, WeatherFragment(), WeatherFragment::class.simpleName)
            .commit()
     //   test()
    }

    private fun test() {

        val aaa = 12.3999955
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_UP
        val shortDouble = df.format(aaa).toDouble()
        logA(shortDouble)

        val longValue = shortDouble.toLong()
        logA(longValue)

        logA(shortDouble==longValue.toDouble())
        (if( shortDouble==longValue.toDouble()) longValue else shortDouble).toString()


      //  return (if (shortDouble - longValue > 0) longValue else shortDouble).toString()
    }
}