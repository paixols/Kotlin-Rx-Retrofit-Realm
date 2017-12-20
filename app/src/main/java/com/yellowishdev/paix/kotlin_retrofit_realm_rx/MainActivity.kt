package com.yellowishdev.paix.kotlin_retrofit_realm_rx

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import com.yellowishdev.paix.kotlin_retrofit_realm_rx.api.GitHubService
import com.yellowishdev.paix.kotlin_retrofit_realm_rx.databinding.ActivityMainBinding
import com.yellowishdev.paix.kotlin_retrofit_realm_rx.model.GitHubUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example of Realm as cache system using RxJava in Kotlin & Retrofit2*/

class MainActivity : AppCompatActivity() {


    /*LifeCycle*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Data binding*/
        val binding: ActivityMainBinding = DataBindingUtil.
                setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        /*Data de-serializing for Realm objects
        * This is required to correctly map to realm when using RetroFit2 with Gson
        * **/
        val gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {

            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return false
            }

            override fun shouldSkipField(f: FieldAttributes?): Boolean {
                return f?.declaredClass == RealmObject::class.java
            }

        }).create()

        /*Retrofit*/
        val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .build()

        val gitHubService: GitHubService = retrofit.create(GitHubService::class.java)


        /*Realm*/
        var realm: Realm? = null
        try {

            realm = Realm.getDefaultInstance()

            print("REALM PATH: " + realm.path) //Used for Realm Browser
            /**
             * To access default.realm file from terminal:
             *
             * $ adb root
             * $ adb devices
             * $ adb pull /data/data/<package name>/files/ .
             *
             * This will copy the emulator or rooted device files into a files folder
             * to your current directory. Double click the default.realm file to open
             * it with Realm Browser
             * */

            /**
             * Get user if it's already saved on [realm] as Cache system
             * or else pull using [RetroFit2] GitHubService in this example
             * */
            val savedUser: GitHubUser? = GitHubUser().queryFirst()
            savedUser?.let {

                /*Update User Interface with realm cached data*/
                updateUI(binding, it)

            } ?: run {

                /*Pull fresh data*/
                gitHubService.getGitHubService("paixols")
                        .subscribeOn(Schedulers.newThread()) //Schedulers.io()
                        .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                        .subscribe(
                                { user ->

                                    user.save()
                                    updateUI(binding, user)

                                },
                                { error ->

                                    print("Realm Error: " + error.message)

                                }
                        )
            }

        } catch (e: Throwable) {

            /*Do not leave realm transactions open*/
            if (realm != null && realm.isInTransaction) {
                realm.cancelTransaction()
            }
            //Stack trace
            print("REALM ERROR: " + e.stackTrace)

        } finally {
            /*Do not leave realm transactions open*/
            realm?.close()
        }

    }

    /*Update User interface with data binding*/
    private fun updateUI(binding: ActivityMainBinding, savedUser: GitHubUser?) {

        /*Avatar*/
        Picasso.with(this).load(savedUser?.avatar_url).into(binding.userImage)

        /*User Name*/
        binding.userName.text = savedUser?.name

        /*Public repositories*/
        val publicRepos = resources.getString(R.string.publicRepos) + savedUser?.public_repos.toString()
        binding.publicRepos.text = publicRepos


    }

}
