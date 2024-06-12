package com.example.pleszew.komunikacja_miejska.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.komunikacja_miejska.data.KomunikacjaMiejskaRepository
import com.example.pleszew.komunikacja_miejska.data.start.Stops
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class KomunikacjaMiejskaViewModel @Inject constructor(
    private val komunikacjaMiejskaRepository: KomunikacjaMiejskaRepository
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isActive = MutableStateFlow(false)
    val isActive = _isActive.asStateFlow()

    private val _stops = MutableStateFlow<List<Stops>>(listOf())
    @OptIn(FlowPreview::class)
    val stops = searchText
        .debounce(500L)
        .combine(_stops) { text, stops ->
            if (text.isBlank()) {
                stops
            } else {
                stops.filter {
                    it.doesMarchSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _stops.value
        )

    fun updateSearchText(text: String) {
        _searchText.value = text
    }

    fun updateIsActive(value: Boolean) {
        _isActive.value = value
    }
}