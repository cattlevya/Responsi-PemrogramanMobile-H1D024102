package com.example.getar.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = CoralPinkLight,
    onPrimary = Color.White,
    primaryContainer = CoralPinkLight,
    onPrimaryContainer = Color.White,
    secondary = DeepTealLight,
    onSecondary = Color.White,
    secondaryContainer = DeepTealLight,
    onSecondaryContainer = Color.White,
    tertiary = ButterYellowLight,
    onTertiary = InkText,
    tertiaryContainer = ButterYellowLight,
    onTertiaryContainer = InkText,
    background = CreamBackground,
    onBackground = InkText,
    surface = CardWhite,
    onSurface = InkText,
    surfaceVariant = SurfaceMuted,
    onSurfaceVariant = SubtitleGreyLight,
    outline = SubtitleGreyLight
)

private val DarkColorScheme = darkColorScheme(
    primary = CoralPinkDark,
    onPrimary = CanvasDark,
    primaryContainer = CoralPinkDark,
    onPrimaryContainer = TextPale,
    secondary = DeepTealDark,
    onSecondary = CanvasDark,
    secondaryContainer = DeepTealDark,
    onSecondaryContainer = CanvasDark,
    tertiary = ButterYellowDark,
    onTertiary = CanvasDark,
    tertiaryContainer = ButterYellowDark,
    onTertiaryContainer = CanvasDark,
    background = CanvasDark,
    onBackground = TextPale,
    surface = CardDark,
    onSurface = TextPale,
    surfaceVariant = SurfaceDark,
    onSurfaceVariant = SubtitleGreyDark,
    outline = SubtitleGreyDark
)

@Composable
fun GetarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
