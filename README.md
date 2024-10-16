
動物好きだから　動物の画像を貰えるアプリを作ってみました！

使った　API　のサイト： https://dog.ceo/dog-api/


追加に使った　implementation

  implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    // nvigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.2")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")


    //viewmodel lifecycle livedata
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //gif
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.29")

    // Koin for Android
    implementation("io.insert-koin:koin-android:4.0.0")
    implementation("io.insert-koin:koin-core-viewmodel:4.0.0")


    //room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
