package com.gnua_aruht.cinemate.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.gnua_aruht.cinemate.data.db.model.Trailer

@Dao
abstract class TrailerDao {
    @Query(
        """
            SELECT *
            FROM trailer
        """
    )
    abstract suspend fun getTrailers() : List<Trailer>
}