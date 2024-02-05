package dev.jameshill.weatherreport

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// This class is used to store the weather information that is returned from the API call
data class WeatherInfo(
    var humidity: Double? = null,
    var temperature: Double? = null,
)

// This class is used to fetch the weather information from Firebase
class FirebaseDataManager {
    // Create an instance of the Firebase database
    private val database = Firebase.database

    // Create a reference to the FirebaseIOT node in the database
    private val ref = database.getReference("FirebaseIOT")


    fun fetchDataFromFirebase(weatherInfoState: MutableState<WeatherInfo?>) {
        // Add a listener to the FirebaseIOT node in the database
        ref.addValueEventListener(object : ValueEventListener {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val humidity = dataSnapshot.child("humidity").getValue(Double::class.java)
                val temperature = dataSnapshot.child("temperature").getValue(Double::class.java)

                // Create an instance of the WeatherInfo class and set the humidity and temperature
                val weatherInfo = WeatherInfo(humidity, temperature)
                // Set the weatherInfoState to the weatherInfo object
                weatherInfoState.value = weatherInfo
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException())
            }
        })
    }
}

