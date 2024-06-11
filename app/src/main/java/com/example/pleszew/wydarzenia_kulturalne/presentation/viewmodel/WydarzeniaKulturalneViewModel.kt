package com.example.pleszew.wydarzenia_kulturalne.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.wydarzenia_kulturalne.data.WydarzeniaKulturalneRepository
import com.example.pleszew.wydarzenia_kulturalne.data.main.Event
import com.example.pleszew.wydarzenia_kulturalne.data.main.EventDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WydarzeniaKulturalneViewModel @Inject constructor(
    private val wydarzeniaKulturalneRepository: WydarzeniaKulturalneRepository
): ViewModel() {

    private val _events = MutableStateFlow<List<Event>?>(listOf())
    val events: Flow<List<Event>?> = _events

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        getEvents()
    }

    fun getEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            val eventsMap = wydarzeniaKulturalneRepository.getEvents()
            _events.emit(eventsMap.map { it -> it.asDomainModel() })
            _isLoading.value = false
        }
    }

    private fun EventDto.asDomainModel(): Event {
        return Event(
            eventId = this.eventId.toString(),
            eventName = this.eventName,
            eventDate = this.eventDate,
            eventStart = this.eventStart,
            eventEnd = this.eventEnd
        )
    }
}