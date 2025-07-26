package io.github.vasuthakker.countdownviews.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.vasuthakker.countdownviews.models.ProgressbarDefaults
import io.github.vasuthakker.countdownviews.utils.drawLinearIndicatorTrack
import io.github.vasuthakker.countdownviews.utils.drawLinearIndicatorWithBrush
import kotlinx.coroutines.delay


@Composable
fun CountDownBar(
    modifier: Modifier = Modifier,
    countDown: Int,
    trackColor: Color,
    progressColors: ProgressbarDefaults.ProgressColor? = ProgressbarDefaults.progressColors(),
    delayDuration: Long = 1000L,
    smoothAnimation: Boolean = false,
    strokeWidth: Dp = 10.dp,
    strokeCap: StrokeCap = StrokeCap.Butt,
    onEnd: () -> Unit
) {
    CountDownBarInternal(
        modifier = modifier,
        countDown = countDown,
        trackColor = trackColor,
        progressColors = progressColors,
        delayDuration = delayDuration,
        smoothAnimation = smoothAnimation,
        strokeWidth = strokeWidth,
        strokeCap = strokeCap,
        onEnd = onEnd
    )
}

@Composable
fun CountDownBar(
    modifier: Modifier = Modifier,
    countDown: Int,
    trackColor: Color,
    progressBrush: Brush,
    delayDuration: Long = 1000L,
    smoothAnimation: Boolean = false,
    strokeWidth: Dp = 10.dp,
    strokeCap: StrokeCap = StrokeCap.Butt,
    onEnd: () -> Unit
) {
    CountDownBarInternal(
        modifier = modifier,
        countDown = countDown,
        trackColor = trackColor,
        progressBrush = progressBrush,
        delayDuration = delayDuration,
        smoothAnimation = smoothAnimation,
        strokeWidth = strokeWidth,
        strokeCap = strokeCap,
        onEnd = onEnd
    )
}


@Composable
private fun CountDownBarInternal(
    modifier: Modifier = Modifier,
    countDown: Int,
    trackColor: Color = Color.Black,
    progressColors: ProgressbarDefaults.ProgressColor? = null,
    progressBrush: Brush? = null,
    delayDuration: Long,
    smoothAnimation: Boolean,
    strokeWidth: Dp,
    strokeCap: StrokeCap,
    onEnd: () -> Unit
) {
    var progress by remember {
        mutableFloatStateOf(countDown.toFloat())
    }

    LaunchedEffect(key1 = progress)
    {
        if (progress > 0) {
            delay(delayDuration)
            progress--
        } else {
            onEnd.invoke()
        }
    }

    val progressFloat = progress / countDown

    val progressValue by animateFloatAsState(
        targetValue = progressFloat,
        label = "progress", animationSpec = tween(delayDuration.toInt())
    )

    val smoothAnimationValue = remember {
        Animatable(1f)
    }
    LaunchedEffect(Unit) {
        smoothAnimationValue.animateTo(
            0f,
            animationSpec = tween((delayDuration * (countDown + 1)).toInt(), easing = LinearEasing)
        )
    }

    progressColors?.let { progressColors ->
        val progressColor by animateColorAsState(
            targetValue = when {
                progressFloat < 0.1f || smoothAnimationValue.value < 0.1f -> progressColors.endColor
                progressFloat < 0.55f || smoothAnimationValue.value < 0.55f -> progressColors.midColor
                progressFloat > 0.55f || smoothAnimationValue.value > 0.55f -> progressColors.startColor
                else -> Color.Red
            },
            label = "color"
        )

        LinearProgressIndicator(
            progress = if (smoothAnimation) smoothAnimationValue.value else progressValue,
            modifier = modifier.height(strokeWidth),
            trackColor = trackColor,
            color = progressColor,
            strokeCap = strokeCap,

        )
    }

    progressBrush?.let { brush ->
        val coercedProgress = progress.coerceIn(
            0f,
            if (smoothAnimation) smoothAnimationValue.value else progressValue
        )
        Canvas(
            modifier
                // Ensure progressSemantics is available/copied
                .progressSemantics(coercedProgress)
                // Ensure LinearIndicatorWidth, LinearIndicatorHeight are available/copied
                .fillMaxWidth()
                .height(strokeWidth)
        ) {
            val strokeWidth = size.height
            // Call your copied/original track drawing function
            // This assumes drawLinearIndicatorTrack is available in your scope
            drawLinearIndicatorTrack(
                0f,
                1f,
                color = trackColor,
                size.height,
                strokeCap
            )

            // Call your modified progress drawing function
            drawLinearIndicatorWithBrush(0f, coercedProgress, progressBrush, strokeWidth, strokeCap)
        }
    }

}



