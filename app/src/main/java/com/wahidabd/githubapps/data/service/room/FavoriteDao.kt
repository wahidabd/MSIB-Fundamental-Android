package com.wahidabd.githubapps.data.service.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahidabd.githubapps.data.model.FavoriteData
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: FavoriteData)

    @Query("SELECT * FROM favorite_entity ORDER BY updated_at DESC")
    fun getList(): Flow<List<FavoriteData>>

    @Query("SELECT * FROM favorite_entity WHERE login = :login")
    fun detail(login: String): Flow<FavoriteData>

    @Query("DELETE FROM favorite_entity WHERE login = :login")
    fun delete(login: String)
}