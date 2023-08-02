package com.anafthdev.todo.foundation.common

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

object Formatter {

    val mediumDateFormatter: DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)

    val hourMinuteFormatter: SimpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

}