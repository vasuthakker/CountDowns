package com.vktechs.numericals.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.coroutines.delay

@Composable
fun CountDownGaugeWithArrow(
    modifier: Modifier = Modifier, countDown: Int, color: Color, strokeWidth: Float = 5f,
    onEnd: () -> Unit
) {

    var progress by remember {
        mutableFloatStateOf(countDown.toFloat())
    }

    LaunchedEffect(key1 = progress)
    {
        if (progress > 0) {
            delay(1000L)
            progress--
        } else {
            onEnd.invoke()
        }
    }

    val progressFloat = progress / countDown

    val progressValue by animateFloatAsState(
        targetValue = progressFloat * 180,
        label = "progress", animationSpec = tween(500)
    )

    val progressColor by animateColorAsState(
        targetValue = when {
            progressFloat <= 0f -> Color.Red
            progressFloat < 0.5f && progressFloat > 0 -> Color.Yellow
            progressFloat < 1f && progressFloat > 0.5f -> Color.Green
            else -> Color.Green
        },
        label = "color"
    )

    Box(
        modifier = modifier
            .aspectRatio(1f, false)
            .drawWithCache {
                onDrawBehind {
                    drawArc(
                        progressColor,
                        sweepAngle = 180f,
                        startAngle = 180f,
                        useCenter = false,
                        style = Stroke(strokeWidth)
                    )
                }
            },
    ) {
        Canvas(modifier = Modifier.fillMaxSize().rotate(180+progressValue))
        {
            val path = Path().apply {
                moveTo(size.width / 2, size.height / 2)
                lineTo(size.width / 2 + size.width / 4f, size.height / 2 - 20f)
                lineTo(size.width, size.height / 2)
                lineTo(size.width / 2 + size.width / 4, size.height / 2 + 20f)
                close()
            }
            drawPath(path = path, Color.Blue)
        }
    }


}
