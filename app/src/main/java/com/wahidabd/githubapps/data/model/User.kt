package com.wahidabd.githubapps.data.model

data class User(
    val id: Long,
    val login: String,
    val avatar_url: String,
    val name: String? = null,
    val public_repos: Int? = null,
    val public_gists: Int? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val blog: String? = null,
    val company: String? = null,
    val location: String? = null,
    val bio: String? = null
)
