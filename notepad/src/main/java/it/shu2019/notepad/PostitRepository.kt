package it.shu2019.notepad

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-18.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
object PostitRepository {
    private var list: MutableList<Postit> = mutableListOf()

    fun add(postit: Postit) {
        list.add(postit)
    }

    fun getList(): List<Postit> {
        return list
    }

}