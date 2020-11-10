# Multi APP
## Intended User:

We target everyone who want to learn something new or chat with other or use some simple tools like : calculator and degrees converter.

## Describtion:
With this app, you can log in with a Google Account or register a new account  and also you can play and use some useful tools like : 
###  Entertainment side: 
1. Login Screen: you can login with email and password, or sign in with Google account:

<img width="200" heigth="400" alt="Login Screen" src="https://user-images.githubusercontent.com/28203059/98702279-dcad5600-2382-11eb-9705-48b7e8446e11.png">

2. Chat App: you can chat with your friends through this app in realtime.

<img width="200" heigth="400" alt="Chat Page" src="https://user-images.githubusercontent.com/28203059/98700549-f8aff800-2380-11eb-83d6-c14028c40281.png">

3. Connect 3 Game: you can play connect 3 games with your friends.

<img width="200" heigth="400" alt="Connect 3 Game Screen" src="https://user-images.githubusercontent.com/28203059/98700733-30b73b00-2381-11eb-96cb-fb5c676bc382.png">

4. Media Players: You can play and enjoy some music and videos and more.

<img width="200"  height="400" alt="Media App Screen" src="https://user-images.githubusercontent.com/28203059/98700977-76740380-2381-11eb-891d-ebf0c91b51a3.png">

***

### Tools side: 
1. Calculator: You can use a calculator and make simple operations.

<img width="200" height="400" alt="Calculator Screen" src="https://user-images.githubusercontent.com/28203059/98701414-eda99780-2381-11eb-9cde-4577ce7b7f95.png">


2. JavaTpoint Website: You can view the Java T Point website and study more in the programming language in tech fields.

<img width="200" heigth="400" alt="Java T Point Screen" src="https://user-images.githubusercontent.com/28203059/98701595-20539000-2382-11eb-8bbd-8d0e68e70b73.png">

3. Temperature Converter: You can convert the degrees from celsius and fahrenhiet and the oppisite.

<img width="200" height="400" alt="Temperature Converter Screen" src="https://user-images.githubusercontent.com/28203059/98701702-40834f00-2382-11eb-9897-6b3d5cc20404.png">

4. Google Maps: You can get your current location using Google Maps.

<img width="200" height="400" alt="Google Maps Screen" src="https://user-images.githubusercontent.com/28203059/98701809-5abd2d00-2382-11eb-8e02-9d4a38eaf0b5.png">

5. Mobile PDF: You can see the basic and fundamental topics in mobile development.

<img width="200" heigth="400" alt="Mobile Track PDF Screen" src="https://user-images.githubusercontent.com/28203059/98702106-b12a6b80-2382-11eb-9db8-eb80324163a7.png">

---

## Features i used:

- App is written solely in the Java programming language.
- Android Studio version 4.1.0
- Gradle version 6.5
- Get current location using Google Maps API
- Firebase Authentication
- Firebase Database
- Firebase Storage
- Adding UI Widget
- Java language will be used for development in all this designs
- All versions of gradle and android studio are stable.
---

# Gradle versions i used :

```
 implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'


    //design
    //noinspection GradleCompatible

    /this for drawer Layout
    implementation "androidx.drawerlayout:drawerlayout:1.1.1" 
    
    //This for using rounded image
    implementation 'com.makeramen:roundedimageview:2.3.0'
    
    //Loading image
    implementation 'com.squareup.picasso:picasso:2.5.2'


    // sdp library
    implementation 'com.intuit.sdp:sdp-android:1.0.6'

    //Maps
    implementation 'com.google.android.gms:play-services-maps:17.0.0'
    implementation 'com.google.android.gms:play-services-location:17.1.0'

    //butterKnife
    implementation 'com.jakewharton:butterknife:10.2.3'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.3'
    
    //for Navigation Drawer
    implementation 'androidx.navigation:navigation-fragment:2.3.1'
    implementation 'androidx.navigation:navigation-ui:2.3.1'
    
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'

    //ExoPlayer
    implementation 'com.google.android.exoplayer:exoplayer:2.12.0'
    implementation 'com.google.android.exoplayer:exoplayer-core:2.12.0'
    implementation 'com.google.android.exoplayer:exoplayer-dash:2.12.0'
    implementation 'com.google.android.exoplayer:exoplayer-ui:2.12.0'

    //PDF Viewer
    implementation 'com.github.barteksc:android-pdf-viewer:2.8.2'

    //Firebase
    implementation 'com.google.firebase:firebase-database:19.5.1' //firebase database
    implementation 'com.google.firebase:firebase-auth:20.0.0'     //firebase authentication
    implementation 'com.firebaseui:firebase-ui-database:6.4.0'    //firebase UI databse
    implementation 'com.google.firebase:firebase-storage'.        //firebase storage

    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:26.0.0')

    // Declare the dependency for the Cloud Storage library
    // When using the BoM, you don't specify versions in Firebase library dependencies

    //multiDex
    implementation 'com.android.support:multidex:1.0.3'

    //Google Sign-in
    implementation 'com.google.android.gms:play-services-auth:18.1.0'

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.11.0'

    //Design

    implementation 'com.google.android.material:material:1.2.1'

```
