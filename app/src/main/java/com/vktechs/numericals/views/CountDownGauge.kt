package com.vktechs.numericals.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun CountDownGauge(
    modifier: Modifier = Modifier,
    countDown: Int,
    isTextVisible: Boolean = true,
    style: TextStyle = LocalTextStyle.current.copy(fontSize = 50.sp),
    brush: Brush? = null,
    strokeWidth: Float = 5f,
    onEnd: () -> Unit
) {
    CountDownGauge(
        modifier = modifier,
        countDown = countDown,
        brush = brush,
        color = null,
        isTextVisible = isTextVisible,
        style = style,
        strokeWidth = strokeWidth,
        onEnd = onEnd
    )
}

@Composable
fun CountDownGauge(
    modifier: Modifier = Modifier,
    countDown: Int,
    isTextVisible: Boolean = true,
    style: TextStyle = LocalTextStyle.current.copy(fontSize = 50.sp),
    color: Color? = null,
    strokeWidth: Float = 5f,
    onEnd: () -> Unit
) {
    CountDownGauge(
        modifier = modifier,
        countDown = countDown,
        isTextVisible = isTextVisible,
        style = style,
        color = color,
        brush = null,
        strokeWidth = strokeWidth,
        onEnd = onEnd
    )
}


@Composable
private fun CountDownGauge(
    modifier: Modifier = Modifier,
    countDown: Int,
    brush: Brush? = null,
    color: Color? = null,
    strokeWidth: Float = 5f,
    style: TextStyle,
    isTextVisible: Boolean = true,
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

    Box(
        modifier = modifier
            .aspectRatio(2f, false)
            .drawWithCache {
                onDrawBehind {
                    if (brush != null) {
                        drawArc(
                            brush,
                            sweepAngle = progressValue,
                            startAngle = 180f,
                            useCenter = false,
                            size = size.copy(height = size.height * 2),
                            style = Stroke(strokeWidth)
                        )
                    } else {
                        drawArc(
                            color ?: Color.Black,
                            sweepAngle = progressValue,
                            startAngle = 180f,
                            useCenter = false,
                            size = size.copy(height = size.height * 2),
                            style = Stroke(strokeWidth)
                        )
                    }
                }
            },
    ) {
        if (isTextVisible) {
            Text(
                text = progress.toInt().toString(),
                style = style,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }

}

