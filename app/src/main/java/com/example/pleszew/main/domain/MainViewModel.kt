package com.example.pleszew.main.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pleszew.R
import com.example.pleszew.core.data.DrawerScreen
import com.example.pleszew.main.data.HomeViewItems

class MainViewModel: ViewModel() {

    private val _currentScreen: MutableState<DrawerScreen> = mutableStateOf(DrawerScreen.HomePage)

    val currentScreen: MutableState<DrawerScreen>
        get() = _currentScreen

    fun setCurrentScreen(screen: DrawerScreen){
        _currentScreen.value = screen
    }

    val menuItems: List<HomeViewItems> = listOf(
        HomeViewItems(
            name = "Wywóz śmieci",
            route = "wywoz_smieci",
            icon = R.drawable.ic_recycling_24
        ),
        HomeViewItems(
            name = "Komunikacja miejska",
            route = "komunikacja_miejska",
            icon = R.drawable.ic_bus
        ),
        HomeViewItems(
            name = "Wydarzenia kulturalne",
            route = "wydarzenia_kulturalne",
            icon = R.drawable.ic_ticket_96
        ),
        HomeViewItems(
            name = "Miejsca rozrywki",
            route = "miejsca_rozrywki",
            icon = R.drawable.ic_bowling
        ),
        HomeViewItems(
            name = "Miasto i samorząd",
            route = "miasto_samorzad",
            icon = R.drawable.ic_tower
        ),
        HomeViewItems(
            name = "Miasto 15'",
            route = "miasto15",
            icon = R.drawable.ic_stopwatch
        )
    )
}