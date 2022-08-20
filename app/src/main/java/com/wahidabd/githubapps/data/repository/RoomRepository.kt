package com.wahidabd.githubapps.data.repository

import com.wahidabd.githubapps.data.model.FavoriteData
import com.wahidabd.githubapps.data.source.RoomDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject constructor(private val source: RoomDataSource) {
    fun insert(data: FavoriteData) = source.insert(data)
    fun detail(login: String): Flow<FavoriteData> = source.detail(login)
    fun list(): Flow<List<FavoriteData>> = source.list()
    fun delete(login: String) = source.delete(login)
}