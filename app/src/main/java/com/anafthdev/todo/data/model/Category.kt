package com.anafthdev.todo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val userId: Int,
    val name: String,
): Parcelable
