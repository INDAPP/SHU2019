package it.shu2019.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-05-28.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */

fun Context.toast(message: String,
                  duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        this,
        message,
        duration
    ).show()
}

fun Context.toast(messageId: Int,
                  duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        this,
        messageId,
        duration
    ).show()
}

val Activity.preferences: SharedPreferences
    get() {
        return getPreferences(Context.MODE_PRIVATE)
    }