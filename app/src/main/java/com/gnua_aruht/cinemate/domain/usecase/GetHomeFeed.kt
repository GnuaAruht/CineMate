package com.gnua_aruht.cinemate.domain.usecase

import com.gnua_aruht.cinemate.domain.repository.AppRepository
import javax.inject.Inject

class GetHomeFeed @Inject constructor(private val appRepository: AppRepository){

    suspend operator fun invoke() = appRepository.getHomeFeed()

}