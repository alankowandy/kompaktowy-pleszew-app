package com.example.pleszew.wywoz_smieci.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pleszew.wywoz_smieci.data.WywozSmieciRepository
import com.example.pleszew.wywoz_smieci.data.main.GarbageCollection
import com.example.pleszew.wywoz_smieci.data.main.GarbageCollectionDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WywozSmieciViewModel @Inject constructor(
    private val wywozSmieciRepository: WywozSmieciRepository
): ViewModel() {

    private val _routes = MutableStateFlow<List<GarbageCollection>?>(listOf())
    val routes: Flow<List<GarbageCollection>?> = _routes

    private val _selectedName = MutableStateFlow("")
    val selectedName: Flow<String> = _selectedName

    private val _selectedRoute = MutableStateFlow("")
    val selectedRoute: Flow<String> = _selectedRoute

    private val _selectedMonth = MutableStateFlow("")
    val selectedMonth: Flow<String> = _selectedMonth

    private val _selectedRouteId = MutableStateFlow("")
    val selectedRouteId: Flow<String> = _selectedRouteId

    private val _selectedRouteDetails = MutableStateFlow("")
    val selectedRouteDetails: Flow<String> = _selectedRouteDetails

    private val _isEnabled = MutableStateFlow(true)
    val isEnabled: Flow<Boolean> = _isEnabled

    init {
        getGarbageCollection()
    }

    fun getGarbageCollection() {
        viewModelScope.launch {
            val routesMap = wywozSmieciRepository.getGarbageCollection()
            Log.d("done",routesMap.toString())
            val sortedRoutes = routesMap.map { it -> it.asDomainModel() }
            _routes.emit(sortedRoutes)
            //_routes.emit(routesMap.map { it -> it.asDomainModel() })

            _selectedName.value = sortedRoutes.firstOrNull()?.name ?: ""
            val filteredRoutes = sortedRoutes.filter { it.name == _selectedName.value }
            val uniqueRouteNames = filteredRoutes.map { it.routeName }.distinct().sorted()
            _selectedRoute.value = uniqueRouteNames.firstOrNull() ?: ""
            val selectedRoute = filteredRoutes.find { it.routeName == _selectedRoute.value }
            _selectedRouteId.value = selectedRoute?.id ?: ""
            _selectedRouteDetails.value = selectedRoute?.route ?: ""
            val selectedRouteMonths = filteredRoutes.find { it.routeName == _selectedRoute.value }?.month?.firstOrNull() ?: ""
            _selectedMonth.value = selectedRouteMonths
            _isEnabled.value = selectedRoute?.name == "Miasto Pleszew"
        }
    }

    fun updateSelectedName(name: String) {
        _selectedName.value = name
        val filteredRoutes = _routes.value?.filter { it.name == name }
        val uniqueRouteNames = filteredRoutes?.map { it.routeName }?.distinct()?.sorted()
        if (uniqueRouteNames != null) {
            _selectedRoute.value = uniqueRouteNames.firstOrNull() ?: ""
            val selectedRoute = filteredRoutes.find { it.routeName == _selectedRoute.value }
            _selectedRouteId.value = selectedRoute?.id ?: ""
            _selectedRouteDetails.value = selectedRoute?.route ?: ""
            _isEnabled.value = selectedRoute?.name == "Miasto Pleszew"
        }
    }

    fun updateSelectedRoute(route: String) {
        _selectedRoute.value = route
        val selectedRoute = _routes.value?.find { it.routeName == route }
        _selectedRouteId.value = selectedRoute?.id ?: ""
        _selectedRouteDetails.value = selectedRoute?.route ?: ""
    }

    fun updateSelectedMonth(month: String) {
        _selectedMonth.value = month
    }

    private fun GarbageCollectionDto.asDomainModel(): GarbageCollection {
        val monthOrder = listOf(
            "MAJ 2024", "CZERWIEC 2024", "LIPIEC 2024", "SIERPIEŃ 2024", "WRZESIEŃ 2024",
            "PAŹDZIERNIK 2024", "LISTOPAD 2024", "GRUDZIEŃ 2024"
        )

        val sortedMonths = this.month?.sortedWith(compareBy{ monthOrder.indexOf(it) })

        return GarbageCollection(
            id = this.id,
            name = this.name,
            month = sortedMonths!!,
            routeName = this.routeName,
            route = this.route
        )
    }
}