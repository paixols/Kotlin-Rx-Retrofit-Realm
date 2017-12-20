package com.yellowishdev.paix.kotlin_retrofit_realm_rx.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by paix on 12/18/17.
 * Application config
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        /*Configure Realm local database & Build*/
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfig)
    }

}