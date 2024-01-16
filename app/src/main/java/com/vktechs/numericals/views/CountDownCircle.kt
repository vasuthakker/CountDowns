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
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun CountDownCircle(
    modifier: Modifier = Modifier,
    countDown: Int,
    brush: Brush? = null,
    strokeWidth: Float = 10f,
    isTextVisible: Boolean = true,
    delayDuration: Long = 1000,
    style: TextStyle = LocalTextStyle.current.copy(fontSize = 50.sp),
    onEnd: () -> Unit
) {
    CountDownCircle(
        modifier = modifier,
        countDown = countDown,
        brush = brush,
        color = null,
        strokeWidth = strokeWidth,
        isTextVisible = isTextVisible,
        delayDuration = delayDuration,
        style = style,
        onEnd = onEnd
    )
}

@Composable
fun CountDownCircle(
    modifier: Modifier = Modifier,
    countDown: Int,
    color: Color,
    strokeWidth: Float = 10f,
    isTextVisible: Boolean = true,
    delayDuration: Long = 1000,
    style: TextStyle = LocalTextStyle.current.copy(fontSize = 50.sp),
    onEnd: () -> Unit
) {
    CountDownCircle(
        modifier = modifier,
        countDown = countDown,
        color = color,
        brush = null,
        strokeWidth = strokeWidth,
        isTextVisible = isTextVisible,
        delayDuration = delayDuration,
        style = style,
        onEnd = onEnd
    )
}

@Composable
private fun CountDownCircle(
    modifier: Modifier = Modifier,
    countDown: Int,
    brush: Brush? = null,
    color: Color? = null,
    strokeWidth: Float = 10f,
    isTextVisible: Boolean = true,
    delayDuration: Long = 1000,
    style: TextStyle,
    onEnd: () -> Unit
) {

    var progress by remember {
        mutableFloatStateOf(countDown.toFloat())
    }

    LaunchedEffect(key1 = progress)
    {
        delay(delayDuration)
        if (progress > 0) {
            progress--
        } else {
            //secondValue = defaultValue
            onEnd()
        }
    }

    val progressFloat = progress / countDown

    val progressValue by animateFloatAsState(
        targetValue = progressFloat * 360,
        label = "progress", animationSpec = tween(1000)
    )

    Box(
        modifier
            .aspectRatio(1f)
            .drawWithCache {
                onDrawBehind {
                    scale(scaleX = -1f, scaleY = 1f)
                    {
                        if (brush != null) {
                            drawArc(
                                brush,
                                startAngle = -90f,
                                sweepAngle = progressValue,
                                useCenter = false,
                                style = Stroke(strokeWidth)
                            )
                        } else {
                            drawArc(
                                color ?: Color.Black,
                                startAngle = -90f,
                                sweepAngle = progressValue,
                                useCenter = false,
                                style = Stroke(strokeWidth)
                            )
                        }
                    }
                }
            }) {
        if (isTextVisible) {
            Text(
                text = progress.toInt().toString(),
                style = style,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}

@Preview
@Composable
fun PreviewCicle() {
    CountDownCircle(countDown = 100, style = TextStyle(fontSize = 50.sp)) {

    }
}