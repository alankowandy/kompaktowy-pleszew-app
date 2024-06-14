package com.example.pleszew.komunikacja_miejska.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.komunikacja_miejska.data.KomunikacjaMiejskaRepository
import com.example.pleszew.komunikacja_miejska.data.start.SearchedStops
import com.example.pleszew.komunikacja_miejska.data.start.SearchedStopsDto
import com.example.pleszew.komunikacja_miejska.data.start.Stops
import com.example.pleszew.komunikacja_miejska.data.start.StopsDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
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

    private val _stops = MutableStateFlow<List<Stops>>(listOf())

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    init {
        getStops()
        observeSearchText()
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
            _isSearching.value = true
            val stopsSearchedMap = komunikacjaMiejskaRepository.getStopsSearched(searchText)
            _stops.emit(stopsSearchedMap.map { it -> it.asDomainModel() })
            _isSearching.value = false
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchText() {
        viewModelScope.launch {
            searchText
                .debounce(500L)
                .onEach { _isSearching.update { true } }
                .collect { text ->
                    if (text.isBlank()) {
                        _stops.emit(_dataStops.value)
                        _isSearching.value = false
                    } else {
                        getStopsSearched(text)
                    }
                }
        }
    }

    fun getLines(stopId: String) {
        viewModelScope.launch {
            val linesMap = komunikacjaMiejskaRepository.getLines(stopId)
            _lines.emit(linesMap.map { it -> it.asDomainModel() })
            _isCollected.value = true
        }
    }

    @OptIn(FlowPreview::class)
    val stops = _stops.asStateFlow()
//    val stops = searchText
//        .debounce(500L)
//        .combine(_stops) { text, stops ->
//            if (text.isBlank()) {
//                stops
//            } else {
//                stops.filter {
//                    it.doesMarchSearchQuery(text)
//                }
//            }
//        }
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000L),
//            _stops.value
//        )

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