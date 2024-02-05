package dev.jameshill.weatherreport

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherReport() {
    // Create a mutable state variable to hold the weather information
    val weatherInfoState = remember { mutableStateOf<WeatherInfo?>(null) }

    val currentTimeState = remember { mutableStateOf(LocalTime.now()) }
    //  Update current time
    LaunchedEffect(Unit) {
        while (true) {
            currentTimeState.value = LocalTime.now()
            delay(1000)
        }
    }

    // Create an instance of the FirebaseDataManager class
    val firebaseDataManager = FirebaseDataManager()
    LaunchedEffect(Unit) {
        firebaseDataManager.fetchDataFromFirebase(weatherInfoState)
    }


    // Set up Card, Column, and Title
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 50.dp, bottom = 50.dp)
            .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(30.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            // BEGIN TITLE
            Image(
                painterResource(id = R.drawable.baseline_wb_sunny_24),
                contentDescription = "Sunny Symbol",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.Gray, shape = CircleShape)
            )
            Text(

                text = "Time and Weather Service",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.padding(top =8.dp, bottom = 16.dp)
            )
            // END OF TITLE
            // ANALOG CLOCK ELEMENT
            Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnalogClock(currentTimeState.value)

                }
                Spacer(modifier = Modifier.size(24.dp))

            // BEGIN DATA COLUMN
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(top = 24.dp)
            ) {

                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Image(
                        painterResource(id = R.drawable.baseline_time_24),
                        contentDescription = "Time Symbol",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(top = 10.dp)
                    )
                    Text(
                        text = "Time: ${currentTimeState.value.hour}:${currentTimeState.value.minute}:${currentTimeState.value.second}",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Image(
                        painterResource(id = R.drawable.baseline_calendar),
                        contentDescription = "Calendar Symbol",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(top = 10.dp)
                    )
                    Text(
                        text = GetTime(),
                        fontSize = 24.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Image(
                        painterResource(id = R.drawable.baseline_thermometer_24),
                        contentDescription = "Time Symbol",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(top = 10.dp)
                    )

                    Text(
                        text = "Temperature: ${weatherInfoState.value?.temperature}° C",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }


                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Image(
                        painterResource(id = R.drawable.baseline_water_drop_24),
                        contentDescription = "Time Symbol",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(top = 10.dp)
                    )

                    Text(
                        text = "Humidity: ${weatherInfoState.value?.humidity}%",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            } // END OF DATA COLUMN

        } // END OF MAIN COLUMN

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun WeatherReportPreview() {
    WeatherReport()
}