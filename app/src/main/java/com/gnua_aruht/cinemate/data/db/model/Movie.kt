package com.gnua_aruht.cinemate.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey
    val id : Int,
    val title: String,
    val runtime : String,
    val releaseYear : String,
    val rating : Float,
    val poster : String,
    val overview : String,
    val genres : String,
    val showing : Boolean = true
)