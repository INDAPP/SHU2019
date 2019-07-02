package it.shu2019.todos

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * This class is part of SHU2019 project.
 * Created by riccardopizzoni on 2019-07-02.
 * Copyright © 2019 INDAPP
 * info@indapp.it
 */
class TodoApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(config)

        TodosSyncService.startTodosFetch(this)
    }

}