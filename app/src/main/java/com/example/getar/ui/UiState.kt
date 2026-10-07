package com.example.getar.ui

import com.example.getar.data.model.Gempa

sealed interface UiState {
    data object Loading : UiState
    data class Success(val data: List<Gempa>) : UiState
    data class Error(val message: String) : UiState
}
