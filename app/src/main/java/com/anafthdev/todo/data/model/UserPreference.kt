package com.anafthdev.todo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class UserPreference(
    val isGuest: Boolean
): Parcelable, Serializable
