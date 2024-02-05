package dev.jameshill.weatherreport

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnalogClock(currentTime: LocalTime) {

    val seconds = remember { mutableStateOf(currentTime.second) }


   LaunchedEffect(currentTime) {
       while (true) {
           delay(42)
           seconds.value = LocalTime.now().second
       }
   }

    Box(modifier = Modifier.size(150.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            val radius = size.width / 2

            // Draw the clock face
            drawCircle(
                color = Color.Black,
                center = Offset(centerX, centerY),
                radius = radius,
                style = Stroke(width = 2.dp.toPx())
            )

            // Draw the hour hand
            drawLine(
                color = Color.Black,
                start = Offset(centerX, centerY),
                end = calculateHandPosition(
                    centerX,
                    centerY,
                    radius * 0.5,
                    currentTime.hour.toDouble() * 30
                ),
                strokeWidth = 8.dp.toPx()
            )

            // Draw the minute hand
            drawLine(
                color = Color.Black,
                start = Offset(centerX, centerY),
                end = calculateHandPosition(
                    centerX,
                    centerY,
                    radius * 0.7,
                    currentTime.minute.toDouble() * 6
                ),
                strokeWidth = 4.dp.toPx()
            )

            // Draw the second hand
            drawLine(
                color = Color.Red,
                start = Offset(centerX, centerY),
                end = calculateHandPosition(
                    centerX,
                    centerY,
                    radius * 0.8,
                    currentTime.second.toDouble() * 6
                ),
                strokeWidth = 2.dp.toPx()
            )
        }
    }
}

fun calculateHandPosition(centerX: Float, centerY: Float, length: Double, angle: Double): Offset {
    val radians = Math.toRadians(angle - 90)
    val x = centerX + (length * Math.cos(radians)).toFloat()
    val y = centerY + (length * Math.sin(radians)).toFloat()
    return Offset(x, y)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AnalogClockPreview() {
    AnalogClock(currentTime = LocalTime.now())
}