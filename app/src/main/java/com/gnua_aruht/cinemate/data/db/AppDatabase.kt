package com.gnua_aruht.cinemate.data.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gnua_aruht.cinemate.data.db.dao.MovieDao
import com.gnua_aruht.cinemate.data.db.dao.TrailerDao
import com.gnua_aruht.cinemate.data.db.model.Movie
import com.gnua_aruht.cinemate.data.db.model.Trailer
import kotlinx.serialization.json.Json
import java.io.BufferedReader

@Database(
    entities = [
        Movie::class,
        Trailer::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getTrailerDao(): TrailerDao
}


fun SupportSQLiteDatabase.populateAppData(context: Context) {
    populateTrailers(context)
    populateShowingMovies(context)
    populateUpcomingMovies(context)
}

private fun SupportSQLiteDatabase.populateTrailers(context : Context) {
    populateDataList<Trailer>(
        context = context,
        fileName = "trailers.json"
    ) { trailerList ->
        trailerList.forEach { trailer ->
            insert(
                table = "trailer",
                conflictAlgorithm = SQLiteDatabase.CONFLICT_IGNORE,
                values = ContentValues().apply {
                    put("id",trailer.id)
                    put("poster",trailer.poster)
                    put("title",trailer.title)
                }
            )
        }
    }
}

private fun SupportSQLiteDatabase.populateShowingMovies(context: Context) {
    populateMovies(
        context = context,
        fileName = "showing_movies.json",
        showing = true
    )
}

private fun SupportSQLiteDatabase.populateUpcomingMovies(context: Context) {
    populateMovies(
        context = context,
        fileName = "upcoming_movies.json",
        showing = false
    )
}

private fun SupportSQLiteDatabase.populateMovies(
    context: Context,
    showing: Boolean,
    fileName: String
) {
    populateDataList<Movie>(
        context = context,
        fileName = fileName
    ) { moviesList ->
        moviesList.forEach { movie ->
            insert(
                table = "movie",
                conflictAlgorithm = SQLiteDatabase.CONFLICT_IGNORE,
                values = ContentValues().apply {
                    put("id", movie.id)
                    put("title", movie.title)
                    put("runtime", movie.runtime)
                    put("releaseYear", movie.releaseYear)
                    put("rating", movie.rating)
                    put("poster", movie.poster)
                    put("overview", movie.overview)
                    put("genres", movie.genres)
                    put("showing", showing)
                }
            )
        }
    }
}

private inline fun <reified T> SupportSQLiteDatabase.populateDataList(
    context: Context,
    fileName: String,
    execution: SupportSQLiteDatabase.(List<T>) -> Unit
) {
    val jsonString: String = loadJSONFromAssets(context, fileName) ?: return
    val json = Json { ignoreUnknownKeys = true }
    val objectList: List<T> = json.decodeFromString(jsonString)
    execution(objectList)
}


fun loadJSONFromAssets(context: Context, fileName: String): String? {
    return try {
        val inputStream = context.assets.open(fileName)
        val bufferedReader = BufferedReader(inputStream.reader())
        val jsonString = bufferedReader.use { it.readText() }
        jsonString
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}