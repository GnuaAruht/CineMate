package com.gnua_aruht.cinemate.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.gnua_aruht.cinemate.data.db.model.Movie


@Dao
abstract class MovieDao {

    @Query(
        """
            SELECT * FROM 
            movie 
            WHERE showing = 1
        """
    )
    abstract suspend fun getShowingMovies() : List<Movie>

    @Query(
        """
            SELECT * FROM 
            movie 
            WHERE showing = 0
        """
    )
    abstract suspend fun getUpcomingMovies() : List<Movie>

}