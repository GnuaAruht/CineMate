package com.gnua_aruht.cinemate.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gnua_aruht.cinemate.data.db.AppDatabase
import com.gnua_aruht.cinemate.data.db.dao.MovieDao
import com.gnua_aruht.cinemate.data.db.dao.TrailerDao
import com.gnua_aruht.cinemate.data.db.populateAppData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.populateAppData(context)
                }
            }
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.getMovieDao()
    }


    @Provides
    fun provideTrailerDao(appDatabase: AppDatabase): TrailerDao {
        return appDatabase.getTrailerDao()
    }


}
