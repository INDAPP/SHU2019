package it.shu2019.todos

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-25.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */

data class Todo (
    var userId: Int,
    var id: Int,
    var title: String,
    var completed: Boolean
)