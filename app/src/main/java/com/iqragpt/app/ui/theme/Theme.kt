package com.iqragpt.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Light-only Material 3 theme with a soft purple primary color.
 */
@Composable
fun IqraGPTTheme(content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        primary = PurplePrimary,
        onPrimary = Color.White,
        secondary = PurpleSecondary,
        background = LightBackground,
        surface = LightBackground,
        surfaceVariant = LightSurfaceVariant,
        onSurfaceVariant = Color(0xFF49454F)
    )
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
