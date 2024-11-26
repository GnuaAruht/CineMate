package com.gnua_aruht.cinemate.domain.repository


interface AppRepository {
    suspend fun markAsAppHasLaunched()
}