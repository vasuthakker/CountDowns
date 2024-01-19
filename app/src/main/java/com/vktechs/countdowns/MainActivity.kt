package com.vktechs.countdowns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vktechs.countdowns.ui.theme.NumericalsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumericalsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scrollState = rememberScrollState()
                    Column(
                        Modifier
                            .fillMaxSize()
                            .scrollable(scrollState, orientation = Orientation.Vertical),
                    ) {
                   /*     *//* CountDownTimer(
                             modifier = Modifier.align(Alignment.CenterHorizontally),
                             seconds = 4030,
                             style = TextStyle(fontSize = 70.sp)
                         ) {}
                         CountDownView(
                             modifier = Modifier.align(Alignment.CenterHorizontally),
                             countDown = 4030,
                             style = TextStyle(fontSize = 70.sp)
                         ) {}*//*
                        CountDownGauge(
                            countDown = 20,
                            color = Color.Black,
                            strokeWidth = 20f,
                            smoothAnimation = true
                        ) {}

                        CountDownBar(
                            modifier = Modifier.fillMaxWidth(),
                            countDown = 20,
                            trackColor = Color.Black,
                            strokeWidth = 20.dp,
                            smoothAnimation = true
                        ) {}
                        CountDownCircle(
                            strokeWidth = 20f,
                            countDown = 20,
                            style = TextStyle(fontSize = 40.sp),
                            smoothAnimation = true,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {

                        }*/
                    }
                }
            }
        }
    }
}
