package com.gnua_aruht.cinemate.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnua_aruht.cinemate.data.repository.HomeFeed
import com.gnua_aruht.cinemate.domain.usecase.GetHomeFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUIState(
    val loading: Boolean = false,
    val homeFeed: HomeFeed = HomeFeed(),
)


@HiltViewModel
class HomeViewModel @Inject constructor(getHomeFeed: GetHomeFeed) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState>
        get() = _uiState

    init {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            val homeFeed = getHomeFeed()
            _uiState.update {
                it.copy(homeFeed = homeFeed)
            }
        }
    }

}