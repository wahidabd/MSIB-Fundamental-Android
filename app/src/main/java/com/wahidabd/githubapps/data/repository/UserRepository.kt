package com.wahidabd.githubapps.data.repository

import com.wahidabd.githubapps.core.Resource
import com.wahidabd.githubapps.data.model.User
import com.wahidabd.githubapps.data.source.GithubDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val data: GithubDataSource
){

    fun users(): Flow<Resource<List<User>?>> = data.users()

}