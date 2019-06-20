package it.shu2019.notepad

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-18.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
data class Postit(
    var title: String,
    var content: String,
    var color: Int,
    var creation: Long,
    var changed: Long
) {
    constructor() : this("", "", 0xFFFFFF, 0, 0)
}