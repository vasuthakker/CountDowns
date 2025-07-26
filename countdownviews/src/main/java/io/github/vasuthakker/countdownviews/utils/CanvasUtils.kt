package io.github.vasuthakker.countdownviews.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.vasuthakker.countdownviews.utils.drawLinearIndicatorTrack

internal fun DrawScope.drawLinearIndicatorWithBrush(
    startFraction: Float,
    endFraction: Float,
    progressBrush: Brush, // Changed from color: Color
    strokeWidth: Float,
    strokeCap: StrokeCap
    // Add any other parameters the original function had
) {
    drawLine(
        brush = progressBrush, // Use Brush here
        start = Offset(size.width * startFraction, center.y),
        end = Offset(size.width * endFraction, center.y),
        strokeWidth = strokeWidth,
        cap = strokeCap
    )
}

internal fun DrawScope.drawLinearIndicatorTrack(
    startFraction: Float,
    endFraction: Float,
    color: Color, // Changed from color: Color
    strokeWidth: Float,
    strokeCap: StrokeCap
    // Add any other parameters the original function had
) {
    drawLine(
        color = color,
        start = Offset(size.width * startFraction, center.y),
        end = Offset(size.width * endFraction, center.y),
        strokeWidth = strokeWidth,
        cap = strokeCap
    )
}

