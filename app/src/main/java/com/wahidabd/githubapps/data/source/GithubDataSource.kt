package com.wahidabd.githubapps.data.source

import com.wahidabd.githubapps.core.Resource
import com.wahidabd.githubapps.core.SafeCall
import com.wahidabd.githubapps.data.model.Search
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
    fun users(): Flow<Resource<List<User>>> = flow {
        emit(Resource.loading())

        val res = safeCall.enqueue(service::users)
        emit(res)
    }.flowOn(Dispatchers.IO)

    fun search(q: String): Flow<Resource<Search>> = flow {
        emit(Resource.loading())

        val res = safeCall.enqueue(q, service::search)
        emit(res)
    }.flowOn(Dispatchers.IO)

    fun detail(username: String): Flow<Resource<User>> = flow {
        emit(Resource.loading())

        val res = safeCall.enqueue(username, service::detail)
        emit(res)
    }.flowOn(Dispatchers.IO)

    fun followers(username: String): Flow<Resource<List<User>>> = flow {
        emit(Resource.loading())

        val res = safeCall.enqueue(username, service::followers)
        emit(res)
    }.flowOn(Dispatchers.IO)

    fun following(username: String): Flow<Resource<List<User>>> = flow {
        emit(Resource.loading())

        val res = safeCall.enqueue(username, service::following)
        emit(res)
    }.flowOn(Dispatchers.IO)
}