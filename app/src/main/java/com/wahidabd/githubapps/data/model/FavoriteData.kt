package com.wahidabd.githubapps.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_entity")
data class FavoriteData(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "login")
    val login: String,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "avatar")
    val avatar: String? = null,

    @ColumnInfo(name = "updated_at")
    val updated_at: String? = null
)