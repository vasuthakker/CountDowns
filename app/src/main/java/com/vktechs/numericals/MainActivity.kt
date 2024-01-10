package com.vktechs.numericals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.vktechs.numericals.ui.theme.NumericalsTheme
import com.vktechs.numericals.views.CountDownBar
import com.vktechs.numericals.views.CountDownGauge
import com.vktechs.numericals.views.CountDownTimer
import com.vktechs.numericals.views.CountDownView

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
                    Column(Modifier.fillMaxSize()) {
                        CountDownTimer(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            seconds = 4030,
                            style = TextStyle(fontSize = 70.sp)
                        ) {}
                        CountDownView(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            countDown = 4030,
                            style = TextStyle(fontSize = 70.sp)
                        ) {}
                        CountDownGauge(countDown = 10, color = Color.Black, strokeWidth = 20f){}

                        CountDownBar(modifier = Modifier.fillMaxWidth(),countDown = 10, color = Color.Black, strokeWidth = 20f){}
                    }
                }
            }
        }
    }
}
