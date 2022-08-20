package com.wahidabd.githubapps.data.source

import com.wahidabd.githubapps.data.model.FavoriteData
import com.wahidabd.githubapps.data.service.room.MyDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomDataSource @Inject constructor(private val database: MyDatabase) {

    fun insert(data: FavoriteData) = database.favoriteDao().insert(data)
    fun detail(login: String): Flow<FavoriteData> = database.favoriteDao().detail(login)
    fun list(): Flow<List<FavoriteData>> = database.favoriteDao().getList()
    fun delete(login: String) = database.favoriteDao().delete(login)

}