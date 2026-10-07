package com.example.getar.util

import androidx.compose.ui.graphics.Color
import com.example.getar.ui.theme.ButterYellowDark
import com.example.getar.ui.theme.ButterYellowLight
import com.example.getar.ui.theme.CoralPinkDark
import com.example.getar.ui.theme.CoralPinkLight
import com.example.getar.ui.theme.DeepTealDark
import com.example.getar.ui.theme.DeepTealLight

fun String?.orDash(): String = this?.takeIf { it.isNotBlank() } ?: "-"

fun String?.toMagnitudeDouble(): Double = this?.toDoubleOrNull() ?: 0.0

fun Double.magnitudeColor(isDark: Boolean = false): Color = when {
    this >= 6.0 -> if (isDark) CoralPinkDark else CoralPinkLight
    this >= 5.0 -> if (isDark) ButterYellowDark else ButterYellowLight
    else -> if (isDark) DeepTealDark else DeepTealLight
}
