# GuideMe-BangkitCapstone-MobileDevelopment
![Wherever you go, you know](https://user-images.githubusercontent.com/69246482/172908910-316bf9df-a08b-43ae-895f-dba1624bd25f.png)

## App Description
This application was built with the aim of helping tourists. tourists who want to get more detailed information about the historical places they visit can use this application. This application provides services that can help users get information about historical places quickly, easily, and accurately. by using Machine Learning this application can recognize the places they visit by just taking photos and immediately providing information about the place.

Download Apk : https://drive.google.com/file/d/1d18TdwcqjPboeGddC072SOnPPA24VIjC/view?usp=sharing

### Note
<b>For now, this application can only be used in : </b>
- Prambanan temple
- Monas (National Monument)

<b>In the future we will update so that it can be used for other historical tourist attractions.</b>

### Screenshots
![AppScreenshots](https://user-images.githubusercontent.com/69246482/173273173-a67a42df-7e6d-4dc3-9dbc-ebd3d3c44225.png)


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
