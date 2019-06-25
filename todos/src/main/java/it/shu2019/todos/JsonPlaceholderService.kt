package it.shu2019.todos

import retrofit2.Call
import retrofit2.http.GET

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-06-25.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
interface JsonPlaceholderService {

    @GET("todos")
    fun getTodos() : Call<List<Todo>>

}