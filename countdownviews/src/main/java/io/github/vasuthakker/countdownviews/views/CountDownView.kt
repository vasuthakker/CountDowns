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

@Composable
fun CountDownView(
    modifier: Modifier = Modifier,
    countDown: Int,
    delayDuration: Long = 1000,
    style: TextStyle = LocalTextStyle.current,
    onEnd: () -> Unit
) {

    var numericValue by remember {
        mutableIntStateOf(countDown)
    }

    LaunchedEffect(key1 = numericValue)
    {
        delay(delayDuration)
        if (numericValue > 0) {
            numericValue--
        } else {
            //secondValue = defaultValue
            onEnd()
        }
    }

    Row(modifier) {

        val initials = countDown.toString()
        val newValue = numericValue.toString()

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
