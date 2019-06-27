package it.shu2019.notepad

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-27.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
interface FirebaseHttpService {

    @POST("send")
    fun send(
        @Body notification: PushNotification,
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") authorization: String
    ) : Call<Message>

}