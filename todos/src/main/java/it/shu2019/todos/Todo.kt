package it.shu2019.todos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-25.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */

open class Todo (
    var userId: Int? = null,
    @PrimaryKey var id: Int = 0,
    var title: String? = null,
    var completed: Boolean = false
) : RealmObject()