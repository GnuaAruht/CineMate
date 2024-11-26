package com.gnua_aruht.cinemate.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.gnua_aruht.cinemate.UserPreferences
import com.gnua_aruht.cinemate.data.datastore.UserPrefSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideUserPrefDataStore(@ApplicationContext context: Context): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = UserPrefSerializer,
            produceFile = { context.dataStoreFile("user_prefs.pb") },
        )
    }

}