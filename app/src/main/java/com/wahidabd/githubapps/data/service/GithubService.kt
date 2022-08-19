package com.wahidabd.githubapps.data.service

import com.wahidabd.githubapps.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface GithubService {

    @GET("users")
    suspend fun users(): Response<List<User>>

}