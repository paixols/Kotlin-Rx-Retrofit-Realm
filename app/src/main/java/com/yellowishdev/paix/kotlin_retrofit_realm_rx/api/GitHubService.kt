package com.yellowishdev.paix.kotlin_retrofit_realm_rx.api

import com.yellowishdev.paix.kotlin_retrofit_realm_rx.model.GitHubUser
import io.reactivex.Observable
import io.realm.RealmObject
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by paix on 12/18/17.
 * Retrofit 2 Interface including Rx integration of the GitHubUser model
 */
interface GitHubService {

    @GET("users/{username}")
    fun getGitHubService(@Path("username") username: String): Observable<GitHubUser>

}