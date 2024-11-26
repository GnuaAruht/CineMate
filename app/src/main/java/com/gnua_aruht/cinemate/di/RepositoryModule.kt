package com.gnua_aruht.cinemate.di

import com.gnua_aruht.cinemate.data.repository.AppRepositoryImpl
import com.gnua_aruht.cinemate.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(repository: AppRepositoryImpl) : AppRepository

}