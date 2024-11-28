package com.gnua_aruht.cinemate.domain.repository

import com.gnua_aruht.cinemate.data.repository.HomeFeed


interface AppRepository {
    suspend fun markAsAppHasLaunched()
    suspend fun getHomeFeed() : HomeFeed
}