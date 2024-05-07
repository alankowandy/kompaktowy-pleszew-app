package com.example.pleszew

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.DrawerScreen.HomeView)

    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen){
        _currentScreen.value = screen
    }

    val menuItems: List<HomeViewItems> = listOf(
        HomeViewItems(
            name = "Wywóz śmieci",
            icon = R.drawable.ic_recycling_24
        ),
        HomeViewItems(
            name = "Komunikacja miejska",
            icon = R.drawable.ic_bus_white
        ),
        HomeViewItems(
            name = "Wydarzenia kulturalne",
            icon = R.drawable.ic_ticket_white
        ),
        HomeViewItems(
            name = "Miejsca rozrywki",
            icon = R.drawable.ic_bowling_white
        ),
        HomeViewItems(
            name = "Miasto i samorząd",
            icon = R.drawable.ic_tower_white
        ),
        HomeViewItems(
            name = "Miasto 15'",
            icon = R.drawable.ic_stopwatch_white
        )
    )
}