package com.example.pleszew.miejsca_rozrywki.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.miejsca_rozrywki.data.details.LocationDetails
import com.example.pleszew.miejsca_rozrywki.data.details.LocationDetailsDto
import com.example.pleszew.miejsca_rozrywki.data.start.MiejscaRozrywkiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val miejscaRozrywkiRepository: MiejscaRozrywkiRepository
): ViewModel() {
    private val _details = MutableStateFlow<List<LocationDetails>?>(listOf())
    val details: Flow<List<LocationDetails>?> = _details

    fun getDeatils(id: String) {
        viewModelScope.launch {
            val detailsMap = miejscaRozrywkiRepository.getDetails(id)
            Log.d("tag", detailsMap.toString())
            _details.emit(detailsMap.map { it -> it.asDomainModel() })
        }
    }

    private fun LocationDetailsDto.asDomainModel(): LocationDetails {
        return LocationDetails(
            locationName = this.locationName,
            locationPhone = this.locationPhone,
            locationEmail = this.locationEmail,
//            locationStreet = this.locationStreet,
//            locationStreetNum = this.locationStreetNum,
//            locationHouseNum = this.locationHouseNum,
//            locationPostal = this.locationPostal,
//            location = this.location,
            locationWebsiteName = this.locationWebsiteName,
            locationWebsite = this.locationWebsite
        )
    }
}