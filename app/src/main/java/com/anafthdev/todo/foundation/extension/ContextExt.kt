package com.anafthdev.todo.foundation.extension

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

fun Context.toast(any: Any?, length: Int = Toast.LENGTH_SHORT) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(this, any?.toString(), length).show()
    }
}
