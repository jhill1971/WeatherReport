package dev.jameshill.weatherreport

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// This function returns the current time in the format of "HH:mm MM-dd-yyyy"
fun GetTime(): String {
    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat("MM-dd-yyyy", Locale("en", "US"))
    val current = formatter.format(time)
    return current
}
