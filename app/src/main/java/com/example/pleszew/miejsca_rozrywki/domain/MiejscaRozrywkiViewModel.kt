package com.example.pleszew.miejsca_rozrywki.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.miejsca_rozrywki.data.start.Locations
import com.example.pleszew.miejsca_rozrywki.data.start.LocationsDto
import com.example.pleszew.miejsca_rozrywki.data.start.MiejscaRozrywkiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MiejscaRozrywkiViewModel @Inject constructor(
    private val miejscaRozrywkiRepository: MiejscaRozrywkiRepository
): ViewModel() {

    private val _locations = MutableStateFlow<List<Locations>?>(listOf())
    val locations: Flow<List<Locations>?> = _locations

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        getLocations()
    }

    fun getLocations() {
        viewModelScope.launch {
            _isLoading.value = true
            val locationsMap = miejscaRozrywkiRepository.getLocations()
            _locations.emit(locationsMap.map { it -> it.asDomainModel() })
            _isLoading.value = false
        }
    }

    private fun LocationsDto.asDomainModel(): Locations {
        return Locations(
            id = this.id.toString(),
            name = this.name
        )
    }

}