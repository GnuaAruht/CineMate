package com.gnua_aruht.cinemate.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "trailer")
data class Trailer(
    @PrimaryKey
    val id : Int,
    val poster : String,
    val title : String,
)