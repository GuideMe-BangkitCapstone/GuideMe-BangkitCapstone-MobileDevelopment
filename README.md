# GuideMe-BangkitCapstone-MobileDevelopment
![Wherever you go, you know](https://user-images.githubusercontent.com/69246482/172908910-316bf9df-a08b-43ae-895f-dba1624bd25f.png)

## App Description
This application was built with the aim of helping tourists. tourists who want to get more detailed information about the historical places they visit can use this application. This application provides services that can help users get information about historical places quickly, easily, and accurately. by using Machine Learning this application can recognize the places they visit by just taking photos and immediately providing information about the place.

### Screenshots
![AppScreenshots](https://user-images.githubusercontent.com/69246482/173019602-6df5c247-a902-4978-b308-46f8a066ef3c.png)


## Development

#### Requirements
* A Mac or Windows computer.
* Android Studio
* Android Virtual Device (AVD) > API 21

#### Dependencies
```Gradle
dependencies {
    // ViewModel, LiveData, & DataStore
    implementation "androidx.datastore:datastore-preferences:1.0.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1"
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6"
    implementation "androidx.security:security-crypto:1.1.0-alpha03"
    // Glide
    implementation 'com.github.bumptech.glide:glide:4.13.1'
    // Camera
    implementation "androidx.camera:camera-camera2:1.2.0-alpha02"
    implementation "androidx.camera:camera-lifecycle:1.2.0-alpha02"
    implementation "androidx.camera:camera-view:1.2.0-alpha02"
}
```

#### Plugins
```Gradle
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-parcelize'
    id 'kotlin-kapt'
}
```
### Clone this App

**Clone**
```bash
$ git clone https://github.com/GuideMe-BangkitCapstone/GuideMe-BangkitCapstone-MobileDevelopment.git
```

**Open in Android Studio**
* `File -> Open -> Navigate to folder that you clone this repo -> Open`

**Run this project on AVD**
* `Start AVD -> Run 'app'`

**Build this app**
* `Build -> Build Bundle(s)/APK(s) -> Build APK(s)`
