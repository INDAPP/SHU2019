package it.shu2019.utils

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-05-30.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
object Images {

    fun getUrls(
        number: Int,
        width: Int = 1080,
        height: Int = 1920
    ) = (0 until number).map {
        "https://picsum.photos/$width/$height/?image=$it"
    }

}