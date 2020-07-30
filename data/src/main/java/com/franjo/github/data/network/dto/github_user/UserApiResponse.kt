package com.franjo.github.data.network.dto.github_user

import com.franjo.github.domain.model.user.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserApiResponse(
    @Json(name = "avatar_url")
    val avatarUrl: String? = "",
    @Json(name = "bio")
    val bio: String? = "",
    @Json(name = "blog")
    val blog: String? = "",
    @Json(name = "company")
    val company: String? = "",
    @Json(name = "created_at")
    val createdAt: String? = "",
    @Json(name = "email")
    val email: String? = "",
    @Json(name = "events_url")
    val eventsUrl: String? = "",
    @Json(name = "followers")
    val followers: Int = 0,
    @Json(name = "followers_url")
    val followersUrl: String? = "",
    @Json(name = "following")
    val following: Int = 0,
    @Json(name = "following_url")
    val followingUrl: String? = "",
    @Json(name = "gists_url")
    val gistsUrl: String? = "",
    @Json(name = "gravatar_id")
    val gravatarId: String? = "",
    @Json(name = "hireable")
    val hireable: String? = "",
    @Json(name = "html_url")
    val htmlUrl: String? = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "location")
    val location: String? = null,
    @Json(name = "login")
    val login: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "node_id")
    val nodeId: String? = "",
    @Json(name = "organizations_url")
    val organizationsUrl: String? = "",
    @Json(name = "public_gists")
    val publicGists: Int = 0,
    @Json(name = "public_repos")
    val publicRepos: Int = 0,
    @Json(name = "received_events_url")
    val receivedEventsUrl: String? = "",
    @Json(name = "repos_url")
    val reposUrl: String? = "",
    @Json(name = "site_admin")
    val siteAdmin: Boolean = false,
    @Json(name = "starred_url")
    val starredUrl: String? = "",
    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String? = "",
    @Json(name = "twitter_username")
    val twitterUsername: String? = "",
    @Json(name = "type")
    val type: String? = "",
    @Json(name = "updated_at")
    val updatedAt: String? = "",
    @Json(name = "url")
    val url: String? = ""
)

fun UserApiResponse.asDomainObject(): User {
    return User(
        name = name.orEmpty(),
        company = company.orEmpty(),
        blog = blog.orEmpty(),
        location = location.orEmpty(),
        email = this.email.orEmpty(),
        hireable = this.hireable.orEmpty(),
        bio = this.bio.orEmpty(),
        publicRepos = this.publicRepos,
        publicGists = this.publicGists,
        followers = this.followers,
        following = this.following,
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
        htmlUrl = this.htmlUrl.orEmpty(),
        url = this.url.orEmpty()
    )

}