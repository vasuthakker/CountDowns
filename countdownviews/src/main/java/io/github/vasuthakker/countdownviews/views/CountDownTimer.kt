package io.github.vasuthakker.countdownviews.views

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.time.Duration.Companion.seconds

@Composable
fun CountDownTimer(
    modifier: Modifier = Modifier,
    seconds: Int,
    delayDuration: Long = 1000,
    style: TextStyle = LocalTextStyle.current,
    addLeadingZero: Boolean = true,
    onEnd: () -> Unit
) {

    var totalSeconds by remember {
        mutableIntStateOf(seconds)
    }

    LaunchedEffect(key1 = totalSeconds)
    {
        delay(delayDuration)
        if (totalSeconds > 0) {
            totalSeconds--
        } else {
            //secondValue = defaultValue
            onEnd()
        }
    }

    Row(modifier) {

        val initials = seconds.formatTime(addLeadingZero)
        val newValue = totalSeconds.formatTime(addLeadingZero)

        for (i in initials.indices) {
            val oldChar = initials.getOrNull(i)
            val newChar = newValue.getOrNull(i)
            val char = if (oldChar == newChar) {
                oldChar
            } else {
                newChar
            }

            if (char != null) {
                AnimatedContent(targetState = char, label = "animation",
                    transitionSpec = {
                        slideInVertically { it } togetherWith slideOutVertically { -it }
                    }
                ) { charValue ->
                    Text(text = charValue.toString(), style = style)
                }
            }

        }
    }
}

private fun Int.formatTime(addLeadingZero: Boolean): String {
    this.seconds.toComponents { hours, minutes, seconds, _ ->
        return String.format(
            Locale.getDefault(),
            if (addLeadingZero) "%02d:%02d:%02d" else "%d:%d:%d",
            hours,
            minutes,
            seconds,
        )
    }
}

private fun Int.addLeadingZero(required: Boolean = true): String {
    return if (required) String.format("%02d", (this)) else this.toString()
}
