package com.gnua_aruht.cinemate.data.repository

import androidx.datastore.core.DataStore
import com.gnua_aruht.cinemate.UserPreferences
import com.gnua_aruht.cinemate.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val userPrefs: DataStore<UserPreferences>
) : AppRepository {

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