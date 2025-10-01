package com.behnamuix.popcorn.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

// 🎨 فقط Dark ColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    error = Error,
    background = Background,
    surface = Surface,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackground,
    onSurface = OnSurface,
    onError = OnError,
    onTertiary = IMDB_COLOR
)

@Composable
fun PopCornTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = AppTypography
    ) {
        CompositionLocalProvider(
            LocalSpacing provides Spacing()
        ) {
            content()
        }
    }
}
