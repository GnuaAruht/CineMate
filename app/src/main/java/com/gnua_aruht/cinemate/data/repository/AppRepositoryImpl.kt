package com.gnua_aruht.cinemate.data.repository

import androidx.datastore.core.DataStore
import com.gnua_aruht.cinemate.UserPreferences
import com.gnua_aruht.cinemate.data.db.dao.MovieDao
import com.gnua_aruht.cinemate.data.db.dao.TrailerDao
import com.gnua_aruht.cinemate.data.db.model.Movie
import com.gnua_aruht.cinemate.data.db.model.Trailer
import com.gnua_aruht.cinemate.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class HomeFeed(
    val trailers: List<Trailer> = emptyList(),
    val showingMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
) {
    val isEmpty: Boolean
        get() = trailers.isEmpty() && showingMovies.isEmpty() && upcomingMovies.isEmpty()
}

class AppRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val trailerDao: TrailerDao,
    private val userPrefs: DataStore<UserPreferences>
) : AppRepository {

    override suspend fun getHomeFeed(): HomeFeed = coroutineScope {
        val trailersValue = async { trailerDao.getTrailers() }
        val showingMovies = async { movieDao.getShowingMovies() }
        val upcomingMovies = async { movieDao.getUpcomingMovies() }
        HomeFeed(
            trailers = trailersValue.await(),
            showingMovies = showingMovies.await(),
            upcomingMovies = upcomingMovies.await()
        )
    }

    override suspend fun markAsAppHasLaunched() {
        withContext(Dispatchers.IO) {
            userPrefs.updateData { pref ->
                pref.toBuilder()
                    .setAppHaveLaunched(true)
                    .build()
            }
        }
    }


}