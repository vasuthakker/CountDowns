package io.github.vasuthakker.countdownviews.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


object ProgressbarDefaults {

    data class ProgressColor private constructor(
        val startColor: Color,
        val midColor: Color,
        val endColor: Color
    ) {

        companion object {
            @Composable
            fun default(startColor: Color, midColor: Color, endColor: Color): ProgressColor {
                return ProgressColor(
                    startColor = startColor,
                    midColor = midColor,
                    endColor = endColor
                )
            }
        }
    }

    @Composable
    fun progressColors(
        startColor: Color = Color.Green,
        midColor: Color = Color.Yellow,
        endColor: Color = Color.Red
    ): ProgressColor {
        return ProgressColor.default(startColor, midColor, endColor)
    }
}