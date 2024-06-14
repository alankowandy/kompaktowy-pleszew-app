package com.example.pleszew.komunikacja_miejska.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.core.domain.KomunikacjaMiejska
import com.example.pleszew.core.domain.KomunikacjaMiejskaDetails
import com.example.pleszew.komunikacja_miejska.data.KomunikacjaMiejskaRepository
import com.example.pleszew.komunikacja_miejska.data.start.SelectedStop
import com.example.pleszew.komunikacja_miejska.data.start.SelectedStopDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KomunikacjaMiejskaHoursViewModel @Inject constructor(
    private val komunikacjaMiejskaRepository: KomunikacjaMiejskaRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _selectedStop = MutableStateFlow<List<SelectedStop>>(listOf())
    val selectedStop: Flow<List<SelectedStop>> = _selectedStop

    init {
        val stopName = savedStateHandle.get<String>(KomunikacjaMiejskaDetails.stopName)
        val lineId = savedStateHandle.get<String>(KomunikacjaMiejskaDetails.lineId)
        if (stopName != null && lineId != null) {
            getSelectedStop(stopName, lineId)
        }
    }

    fun getSelectedStop(stopName: String, lineId: String) {
        viewModelScope.launch {
            val stopMap = komunikacjaMiejskaRepository.getSelectedStop(stopName, lineId)
            _selectedStop.emit(stopMap.map { it -> it.asDomainModel() })
        }
    }

    private fun SelectedStopDto.asDomainModel(): SelectedStop {
        return SelectedStop(
            id = this.id.toString(),
            stopName = this.stopName,
            departureTimes = this.departureTimes
        )
    }
}