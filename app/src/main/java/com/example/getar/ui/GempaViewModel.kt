package com.example.getar.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getar.data.remote.RetrofitClient
import com.example.getar.data.repository.GempaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GempaViewModel(
    private val repository: GempaRepository = GempaRepository(RetrofitClient.api)
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadGempa()
    }

    fun loadGempa() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                val data = repository.getGempa()
                UiState.Success(data)
            } catch (e: Exception) {
                UiState.Error(e.localizedMessage ?: "Terjadi kesalahan saat memuat data")
            }
        }
    }
}
