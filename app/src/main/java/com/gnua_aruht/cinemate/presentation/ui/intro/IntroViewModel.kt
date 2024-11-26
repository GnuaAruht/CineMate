package com.gnua_aruht.cinemate.presentation.ui.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnua_aruht.cinemate.domain.usecase.SaveAppLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(private val saveAppLaunch: SaveAppLaunch): ViewModel() {

    fun saveAppHasLaunched() {
        viewModelScope.launch {
            saveAppLaunch()
        }
    }

}