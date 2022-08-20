package com.wahidabd.githubapps.data.repository

import com.wahidabd.githubapps.core.Resource
import com.wahidabd.githubapps.data.model.Search
import com.wahidabd.githubapps.data.model.User
import com.wahidabd.githubapps.data.source.GithubDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val data: GithubDataSource
){

    fun users(): Flow<Resource<List<User>>> = data.users()
    fun search(q: String): Flow<Resource<Search>> = data.search(q)
    fun detail(username: String): Flow<Resource<User>> = data.detail(username)
    fun followers(username: String): Flow<Resource<List<User>>> = data.followers(username)
    fun following(username: String): Flow<Resource<List<User>>> = data.following(username)

}