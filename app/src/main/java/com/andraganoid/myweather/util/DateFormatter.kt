package com.andraganoid.myweather.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


object DateFormatter {

    private val now: LocalDate?
        get() = LocalDate.now()

    private lateinit var formatter: DateTimeFormatter
    private lateinit var dateTime: LocalDateTime
    private lateinit var time: LocalTime

    fun todayWeekDay(): String? = now?.dayOfWeek?.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH)

    fun dateToday(): String = "${now?.dayOfMonth}.${now?.month?.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH)}"

    fun weekDayShort(dateString: String?): String {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateTime = LocalDateTime.parse(dateString, formatter)
        return dateTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    fun to24hFormat(timeString: String): String {
        formatter = DateTimeFormatter.ofPattern("hh:mm a");
        time = LocalTime.parse(timeString, formatter)
        return time.toString()
    }

    fun timeFromDate(dateString: String?): String {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        time = LocalTime.parse(dateString, formatter)
        return time.toString()
    }

}