package com.wahidabd.githubapps.data.service

import com.wahidabd.githubapps.BuildConfig
import com.wahidabd.githubapps.data.model.Search
import com.wahidabd.githubapps.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun users(): Response<List<User>>

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun search(
        @Query("q") q: String
    ): Response<Search>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun detail(
        @Path("username") username: String
    ): Response<User>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun followers(
        @Path("username") username: String
    ): Response<List<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun following(
        @Path("username") username: String
    ): Response<List<User>>

}