package com.anafthdev.todo.data.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CategoryColor(
    val id: Int,
    val dark: Boolean,
    val primary: @RawValue Color,
    val onPrimary: @RawValue Color,
    val primaryContainer: @RawValue Color,
    val onPrimaryContainer: @RawValue Color,
    val secondary: @RawValue Color,
    val onSecondary: @RawValue Color,
    val secondaryContainer: @RawValue Color,
    val onSecondaryContainer: @RawValue Color,
    val tertiary: @RawValue Color,
    val onTertiary: @RawValue Color,
    val tertiaryContainer: @RawValue Color,
    val onTertiaryContainer: @RawValue Color,
    val error: @RawValue Color,
    val errorContainer: @RawValue Color,
    val onError: @RawValue Color,
    val onErrorContainer: @RawValue Color,
    val background: @RawValue Color,
    val onBackground: @RawValue Color,
    val surface: @RawValue Color,
    val onSurface: @RawValue Color,
    val surfaceVariant: @RawValue Color,
    val onSurfaceVariant: @RawValue Color,
    val outline: @RawValue Color,
    val inverseOnSurface: @RawValue Color,
    val inverseSurface: @RawValue Color,
    val inversePrimary: @RawValue Color,
    val surfaceTint: @RawValue Color,
): Parcelable
