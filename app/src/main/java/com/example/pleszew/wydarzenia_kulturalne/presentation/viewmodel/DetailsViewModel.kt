package com.example.pleszew.wydarzenia_kulturalne.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.core.domain.WydarzeniaKulturalneDetails
import com.example.pleszew.wydarzenia_kulturalne.data.WydarzeniaKulturalneRepository
import com.example.pleszew.wydarzenia_kulturalne.data.details.EventDetails
import com.example.pleszew.wydarzenia_kulturalne.data.details.EventDetailsDto
import com.example.pleszew.wydarzenia_kulturalne.data.main.Event
import com.example.pleszew.wydarzenia_kulturalne.data.main.EventDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val wydarzeniaKulturalneRepository: WydarzeniaKulturalneRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _event = MutableStateFlow<List<EventDetails>?>(listOf())
    val event: Flow<List<EventDetails>?> = _event

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        val eventId = savedStateHandle.get<String>(WydarzeniaKulturalneDetails.eventId)
        eventId?.let {
            getEventDetails(it)
            Log.d("done", "done")
        }
    }

    fun getEventDetails(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val eventMap = wydarzeniaKulturalneRepository.getEventDetails(id)
            _event.emit(eventMap.map { it -> it.asDomainModel() })
            _isLoading.value = false
        }
    }

    private fun EventDetailsDto.asDomainModel(): EventDetails {
        return EventDetails(
            eventName = this.eventName,
            eventDate = this.eventDate,
            eventStart = this.eventStart,
            eventEnd = this.eventEnd,
            eventImage = this.eventImage,
            eventStreet = this.eventStreet,
            eventStreetNum = this.eventStreetNum,
            eventApartmentNum = this.eventApartmentNum,
            eventDetails = this.eventDetails,
            eventPostal = this.eventPostal,
            eventTown = this.eventTown
        )
    }
}