Weather Report Application
==========================
IOT Prototype by James Hill, 2023
Houston, TX

## Purpose
To display the current system time, and temperature and humidity data fetched from Firebase.

## Technologies Used
1. Android Studio Flamingo
2. Jetpack Compose/Kotlin
3. Firebase
4. Arduino Uno WiFi Rev. 2
5. DHT 11 Temperature and Humidity Sensor

## Process
1. Local Temperature and Humidity data is fetched from the DHT 11 sensor using an Arduino Uno WiFi Rev. 2.
2. The data is sent to Firebase using the Firebase Arduino library.
3. The data is fetched from Firebase using the Firebase Android library.
4. The data is displayed on the Android application using Jetpack Compose.
5. The system time is displayed on the Android application using Jetpack Compose.
6. The data is updated every 5 seconds.

