package it.shu2019.notepad

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-27.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
object Client {
    val moshi: Moshi
    val retrofit: Retrofit
    val firebaseHttpService: FirebaseHttpService


    init {
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/fcm/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        firebaseHttpService = retrofit.create(FirebaseHttpService::class.java)
    }
}