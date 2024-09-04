# 🌦 JetWeather 

A simple, user-friendly weather application built for Android using Jetpack Compose for UI, Retrofit for network requests, TomTom to fetch the user's current location, and Open Meteo API for real-time weather data.

## 📱 Features
- [x] Real-time weather data
- [x] Real-time weather updates for any city using the Open Meteo API.
- [x] Reverse geolocation to fetch the user's current location using TomTom API.
- [x] Beautiful UI built using Jetpack Compose. 
- [x] Display of current temperature, weather condition, wind speed, and more. 
- [x] Dark mode support.
- [ ] Dynamic theming based on the weather condition.
- [ ] Supports searching for multiple cities. 
- [ ] Adaptive UI for different screen sizes (mobile & tablet). 

## 📸 Screenshots
**TBD**

## 🛠 Tech stack
- **Jetpack Compose**: Used for building the UI in a declarative way. 
- **Retrofit**: For making network requests to fetch weather data from the Open Meteo API. 
- **Kotlin Coroutines**: For handling asynchronous operations, ensuring smooth performance. 
- **ViewModel & Flow**: To handle the UI's data in a lifecycle-aware manner. 
- **Material 3 Design**: Following Google's Material Design guidelines to create a clean, modern UI.

## 🌐 API Reference

### Open Meteo API
This app uses the Open Meteo API to fetch real-time weather data.

```bash
https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m
```

- `latitude`: Latitude of the city.
- `longitude`: Longitude of the city.
- `hourly=temperature_2m`: Show the hourly temperature.

For more detailed API documentation, visit the [Open Meteo Documentation](https://open-meteo.com/en/docs).

### TomTom API
This app uses the TomTom API for reverse geolocation to fetch the user's current location.

```bash
https://api.tomtom.com/search/2/reverseGeocode/51.6259881,-0.0449538.json?key={API_KEY}&radius=100
```

- `51.6259881`: Latitude of the user's current location.
- `-0.0449538`: Longitude of the user's current location.
- `key={API_KEY}`: Your TomTom API key.
- `radius=100`: Radius in meters to search for the user's location.

## ⚙️ Setup & Installation

### Prerequisites
- Android Studio Bumblebee (or newer)
- Android SDK 26+ 
- Gradle 7.0 or newer

### Clone the repository:
```bash
git clone https://github.com/Simeon03/JetWeather.git
cd weather-app
```

### Open in Android Studio:
- Open Android Studio.
- Select File > Open and navigate to the project directory.
- Sync Gradle and wait for the project to build.

### Running the app:
- Select an emulator or physical device. 
- Click on the Run button in Android Studio, or use Shift + F10.

## 🔑 API Key Setup:

### Open Meteo API
No API key is needed. 

### TomTom API
API key will be required to fetch the user's current location.

- Create an account on the [TomTom Developer Portal](https://developer.tomtom.com/).
- Create a new project and generate an API key.
- Add the API key to the `local.properties` file:

```properties
tomtom_api=API_KEY
```

- Add the API key to the `build.gradle` file:

```gradle
buildConfigField("String", "TOMTOM_API_KEY", "\"$tomtom_api\"")
```

## 👍 Permissions
You might need to enable internet permissions and grant location permissions:

Add this to your `AndroidManifest.xml` file:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

## 🧱 Architecture
The project follows the MVVM (Model-View-ViewModel) architecture pattern, ensuring a clean separation of concerns.

- **Model**: Handles data operations (e.g., Retrofit for fetching data).
- **ViewModel**: Provides data to the UI and handles logic.
- **View**: The UI built with Jetpack Compose, observing the ViewModel for changes.

## 🧪 Testing
**TBD**

## 🚀 Continuous Integration
**TBD**

## 🛠 Dependencies
- [Jetpack Compose](https://developer.android.com/compose)
- [Retrofit](https://square.github.io/retrofit/)
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Material 3 Design Components](https://m3.material.io/)

## 📝 License
This project is licensed under the **MIT License** - see the LICENSE file for details.

## 📧 Contact
**TBD**