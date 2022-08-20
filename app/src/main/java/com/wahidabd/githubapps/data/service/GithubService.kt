package com.wahidabd.githubapps.data.service

import com.wahidabd.githubapps.data.model.Search
import com.wahidabd.githubapps.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    suspend fun users(): Response<List<User>>

    @GET("search/users")
    suspend fun search(
        @Query("q") q: String
    ): Response<Search>

    @GET("users/{username}")
    suspend fun detail(
        @Path("username") username: String
    ): Response<User>

    @GET("users/{username}/followers")
    suspend fun followers(
        @Path("username") username: String
    ): Response<List<User>>

    @GET("users/{username}/following")
    suspend fun following(
        @Path("username") username: String
    ): Response<List<User>>

}