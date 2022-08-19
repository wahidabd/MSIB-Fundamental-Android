package com.wahidabd.githubapps.data.source

import com.wahidabd.githubapps.core.Resource
import com.wahidabd.githubapps.core.SafeCall
import com.wahidabd.githubapps.data.model.User
import com.wahidabd.githubapps.data.service.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubDataSource @Inject constructor(
    private val service: GithubService,
    private val safeCall: SafeCall
){
    fun users(): Flow<Resource<List<User>?>> = flow {
        emit(Resource.loading())

        val res = safeCall.enqueue(service::users)
        emit(res)
    }.flowOn(Dispatchers.IO)

}