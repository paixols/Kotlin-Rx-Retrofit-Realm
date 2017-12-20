package com.yellowishdev.paix.kotlin_retrofit_realm_rx.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class GitHubUser : RealmObject() {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("login")
    @Expose
    var login: String? = null

    @SerializedName("avatar_url")
    @Expose
    var avatar_url: String? = null

    @SerializedName("gravatar_id")
    @Expose
    var gravatar_id: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("html_url")
    @Expose
    var html_url: String? = null

    @SerializedName("followers_url")
    @Expose
    var followers_url: String? = null

    @SerializedName("following_url")
    @Expose
    var following_url: String? = null

    @SerializedName("gists_url")
    @Expose
    var gists_url: String? = null

    @SerializedName("starred_url")
    @Expose
    var starred_url: String? = null

    @SerializedName("subscriptions_url")
    @Expose
    var subscriptions_url: String? = null

    @SerializedName("organizations_url")
    @Expose
    var organizations_url: String? = null

    @SerializedName("repos_url")
    @Expose
    var repos_url: String? = null

    @SerializedName("events_url")
    @Expose
    var events_url: String? = null

    @SerializedName("received_events_url")
    @Expose
    var received_events_url: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("site_admin")
    @Expose
    var site_admin: Boolean? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("company")
    @Expose
    var company: String? = null

    @SerializedName("blog")
    @Expose
    var blog: String? = null

    @SerializedName("location")
    @Expose
    var location: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("hireable")
    @Expose
    var hireable: String? = null

    @SerializedName("bio")
    @Expose
    var bio: String? = null

    @SerializedName("public_repos")
    @Expose
    var public_repos: Int? = null

    @SerializedName("public_gists")
    @Expose
    var public_gists: Int? = null

    @SerializedName("followers")
    @Expose
    var followers: Int? = null

    @SerializedName("following")
    @Expose
    var following: Int? = null

    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null

}