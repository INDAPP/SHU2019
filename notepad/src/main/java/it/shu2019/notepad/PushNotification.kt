package it.shu2019.notepad

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-27.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
data class PushNotification(
    var priority: String = "high",
    var notification: Notification,
    var condition: String
)