package com.example.pleszew.miasto_samorzad.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.miasto_samorzad.data.MiastoSamorzadRepository
import com.example.pleszew.miasto_samorzad.data.Office
import com.example.pleszew.miasto_samorzad.data.OfficeDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MiastoSamorzadViewModel @Inject constructor(
    private val miastoSamorzadRepository: MiastoSamorzadRepository
): ViewModel() {

    private val _offices = MutableStateFlow<List<Office>?>(listOf())
    val offices: Flow<List<Office>?> = _offices

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        getOffices()
    }

    fun getOffices() {
        viewModelScope.launch {
            _isLoading.value = true
            val officesMap = miastoSamorzadRepository.getOfficeDetails()
            _offices.emit(officesMap.map { it -> it.asDomainModel() })
            _isLoading.value = false
        }
    }

    private fun OfficeDto.asDomainModel(): Office {
        return Office(
            officeId = this.officeId,
            officeName = this.officeName,
            officePhoneNumber = this.officePhoneNumber,
            officeEmail = this.officeEmail,
            officeWebsite = this.officeWebsite,
            officeStreet = this.officeStreet,
            officeHouseNumber = this.officeHouseNumber,
            officeApartmentNumber = this.officeApartmentNumber,
            officePostal = this.officePostal,
            officeTown = this.officeTown
        )
    }

}