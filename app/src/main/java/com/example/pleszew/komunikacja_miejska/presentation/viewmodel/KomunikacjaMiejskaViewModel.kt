package com.example.pleszew.komunikacja_miejska.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.komunikacja_miejska.data.KomunikacjaMiejskaRepository
import com.example.pleszew.komunikacja_miejska.data.start.SearchedStops
import com.example.pleszew.komunikacja_miejska.data.start.SearchedStopsDto
import com.example.pleszew.komunikacja_miejska.data.start.SelectedStop
import com.example.pleszew.komunikacja_miejska.data.start.SelectedStopDto
import com.example.pleszew.komunikacja_miejska.data.start.Stops
import com.example.pleszew.komunikacja_miejska.data.start.StopsDto
import com.example.pleszew.miasto_samorzad.data.Office
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KomunikacjaMiejskaViewModel @Inject constructor(
    private val komunikacjaMiejskaRepository: KomunikacjaMiejskaRepository
): ViewModel() {

    private val _dataStops = MutableStateFlow<List<Stops>>(listOf())
    val dataStops: Flow<List<Stops>> = _dataStops

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isActive = MutableStateFlow(false)
    val isActive = _isActive.asStateFlow()

    private val _lines = MutableStateFlow<List<SearchedStops>>(listOf())
    val lines: Flow<List<SearchedStops>> = _lines

    private val _isCollected = MutableStateFlow(false)
    val isCollected = _isCollected.asStateFlow()

    init {
        getStops()
    }

    fun getStops() {
        viewModelScope.launch {
            val stopsMap = komunikacjaMiejskaRepository.getStops()
            _dataStops.emit(stopsMap.map { it -> it.asDomainModel() })
            _stops.value = _dataStops.value
        }
    }

    fun getStopsSearched(searchText: String) {
        viewModelScope.launch {
            val stopsSearchedMap = komunikacjaMiejskaRepository.getStopsSearched(searchText)
            _stops.emit(stopsSearchedMap.map { it -> it.asDomainModel() })
        }
    }

    fun getLines(stopId: String) {
        viewModelScope.launch {
            val linesMap = komunikacjaMiejskaRepository.getLines(stopId)
            _lines.emit(linesMap.map { it -> it.asDomainModel() })
            _isCollected.value = true
        }
    }

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

    private fun StopsDto.asDomainModel(): Stops {
        return Stops(
            id = this.id.toString(),
            stopName = this.stopName
        )
    }

    private fun SearchedStopsDto.asDomainModel(): SearchedStops {
        return SearchedStops(
            lineId = this.lineId,
            stopOrder = this.stopOrder,
            direction = this.direction,
            stopsOrdered = this.stopsOrdered
        )
    }
}