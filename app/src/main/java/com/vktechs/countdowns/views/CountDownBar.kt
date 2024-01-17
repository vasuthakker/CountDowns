package com.vktechs.countdowns.views

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CountDownBar(
    modifier: Modifier = Modifier,
    countDown: Int,
    trackColor: Color,
    delayDuration: Long = 1000L,
    smoothAnimation: Boolean = false,
    strokeWidth: Dp = 10.dp,
    strokeCap: StrokeCap = StrokeCap.Butt,
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


    val progressColor by animateColorAsState(
        targetValue = when {
            progressFloat < 0.1f || smoothAnimationValue.value < 0.1f -> Color.Red
            progressFloat < 0.55f || smoothAnimationValue.value < 0.55f -> Color.Yellow
            progressFloat > 0.55f || smoothAnimationValue.value > 0.55f -> Color.Green
            else -> Color.Red
        },
        label = "color"
    )

    LinearProgressIndicator(
        progress = if (smoothAnimation) smoothAnimationValue.value else progressValue,
        modifier = modifier.height(strokeWidth),
        trackColor = trackColor,
        color = progressColor,
        strokeCap = strokeCap
    )

}
