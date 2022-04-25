package com.andraganoid.myweather.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


object DateFormatter {

    private val now: LocalDateTime?
        get() = LocalDateTime.now()

    private lateinit var formatter: DateTimeFormatter
    private lateinit var dateTime: LocalDateTime
    private lateinit var time: LocalTime

    fun hourInDay(): Int = now?.hour!!

    fun todayWeekDay(): String? = now?.dayOfWeek?.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH)

    fun dateToday(): String = "${now?.dayOfMonth}.${now?.month?.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH)}"

    fun dateQueryToday(): String = "${now?.year}-${now?.monthValue}-${now?.dayOfMonth}"

    fun to24hFormat(timeString: String): String {
        return try {
            formatter = DateTimeFormatter.ofPattern("hh:mm a")
            time = LocalTime.parse(timeString, formatter)
            time.toString()
        } catch (exc: Exception) {
            ""
        }

    }

    fun timeFromDate(dateString: String?): String {
        return try {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm")
            time = LocalTime.parse(dateString, formatter)
            return time.toString()
        } catch (exc: Exception) {
            ""
        }

    }

    fun dayNameShort(dateString: String?): String =
        dateOnly(dateString).dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).uppercase(Locale.getDefault())

    fun dayDate(dateString: String?): String = "${dateOnly(dateString).dayOfMonth}."

    fun dayMonth(dateString: String?): String =
        dateOnly(dateString).month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).uppercase(Locale.getDefault())

    private fun dateOnly(dateString: String?): LocalDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))


}