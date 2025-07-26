package com.vktechs.countdowns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vktechs.countdowns.ui.theme.NumericalsTheme
import io.github.vasuthakker.countdownviews.enum.CountDownType
import io.github.vasuthakker.countdownviews.models.ProgressbarDefaults
import io.github.vasuthakker.countdownviews.views.CountDownBar
import io.github.vasuthakker.countdownviews.views.CountDownCircle
import io.github.vasuthakker.countdownviews.views.CountDownGauge
import io.github.vasuthakker.countdownviews.views.CountDownTimer
import io.github.vasuthakker.countdownviews.views.CountDownView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumericalsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scrollState = rememberScrollState()


                    LazyVerticalGrid(
                        GridCells.Fixed(2),
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.statusBars)
                            .windowInsetsPadding(WindowInsets.navigationBars)
                            .fillMaxSize()
                    )

                    {
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(stringResource(R.string.countdown_timer))
                                CountDownTimer(
                                    modifier = Modifier,
                                    seconds = 3600,
                                    style = TextStyle(fontSize = 30.sp)
                                ) {}
                            }
                        }
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(stringResource(R.string.countdown_view))
                                CountDownView(
                                    modifier = Modifier,
                                    countDown = 10,
                                    style = TextStyle(fontSize = 30.sp)
                                ) {}
                            }
                        }
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(stringResource(R.string.countdown_gauge))
                                CountDownGauge(
                                    countDown = 20,
                                    brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
                                    strokeWidth = 20f,
                                    smoothAnimation = true,
                                    isTextVisible = true,
                                    countDownType = CountDownType.COUNTDOWN_TEXT,
                                    style = TextStyle(fontSize = 30.sp),
                                    ) {}
                            }
                        }

                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(stringResource(R.string.countdown_bar_colors))
                                CountDownBar(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    countDown = 20,
                                    trackColor = Color.Black,
                                    progressColors = ProgressbarDefaults.progressColors(
                                        startColor = Color.Cyan,
                                        midColor = Color.Magenta,
                                        endColor = Color.Blue
                                    ),
                                    strokeWidth = 20.dp,
                                    smoothAnimation = true
                                ) {}
                            }
                        }

                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                                modifier = Modifier.fillMaxSize().padding(5.dp)
                            ) {
                                Text(stringResource(R.string.countdown_bar_brush))
                                CountDownBar(
                                    modifier = Modifier.fillMaxWidth(),
                                    countDown = 20,
                                    trackColor = Color.Black,
                                    progressBrush = Brush.horizontalGradient(
                                        listOf(
                                            Color.Red,
                                            Color.Blue
                                        )
                                    ),
                                    strokeWidth = 20.dp,
                                    smoothAnimation = true
                                ) {}
                            }
                        }
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Text(stringResource(R.string.countdown_circle))
                                CountDownCircle(
                                    brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
                                    strokeWidth = 20f,
                                    countDown = 20,
                                    style = TextStyle(fontSize = 30.sp),
                                    smoothAnimation = true,
                                    modifier = Modifier,
                                    alignment = Alignment.Center,
                                    countDownType = CountDownType.SIMPLE_TEXT
                                ) {}
                            }
                        }
                    }
                }
            }
        }
    }
}
