package com.example.pleszew.wywoz_smieci.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.core.domain.WywozSmieci
import com.example.pleszew.core.domain.WywozSmieciDetails
import com.example.pleszew.wywoz_smieci.data.WywozSmieciRepository
import com.example.pleszew.wywoz_smieci.data.details.GarbageCollectionDetails
import com.example.pleszew.wywoz_smieci.data.details.GarbageCollectionDetailsDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WywozSmieciDetailsViewModel @Inject constructor(
    private val wywozSmieciRepository: WywozSmieciRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _collectionDetails = MutableStateFlow<List<GarbageCollectionDetails>>(listOf())
    val collectionDetails: Flow<List<GarbageCollectionDetails>> = _collectionDetails

    private val _detailName = MutableStateFlow("")
    val detailName: Flow<String> = _detailName

    private val _routeName = MutableStateFlow("")
    val routeName: Flow<String> = _routeName

    private val _collectedRoute = MutableStateFlow("")
    val collectedRoute: Flow<String> = _collectedRoute

    init {
        val id = savedStateHandle.get<String>(WywozSmieciDetails.routeId)
        val month = savedStateHandle.get<String>(WywozSmieciDetails.month)
        if (id != null && month != null) {
            getGarbageCollectionDetails(id = id, month = month)
        }
    }

    fun getGarbageCollectionDetails(id: String, month: String) {
        viewModelScope.launch {
            val collectionMap = wywozSmieciRepository.getGarbageCollectionDetails(
                id = id,
                month = month
            )
            val data = collectionMap.map { it -> it.asDomainModel() }
            _collectionDetails.emit(data)

            _detailName.value = data.firstOrNull()?.name ?: "Miasto"
            _routeName.value = data.firstOrNull()?.routeName ?: "Trasa"
            _collectedRoute.value = data.firstOrNull()?.route ?: ""
        }
    }

    private fun GarbageCollectionDetailsDto.asDomainModel(): GarbageCollectionDetails {
        return GarbageCollectionDetails(
            name = this.name,
            route = this.route,
            routeName = this.routeName,
            garbageType = this.garbageType,
            collectionDate = this.collectionDate
        )
    }
}