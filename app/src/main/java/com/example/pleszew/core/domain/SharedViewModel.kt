package com.example.pleszew.core.domain

import androidx.lifecycle.ViewModel
import com.example.pleszew.core.data.MenuItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


class SharedViewModel: ViewModel() {

    // Dynamiczny stan zmieniający się względem aktywności na której jesteśmy
    private val _currentScreen = MutableStateFlow<MenuItems?>(MenuItems.HomePage)
    val currentScreen: Flow<MenuItems?> = _currentScreen

    private val _title = MutableStateFlow<String?>(MenuItems.HomePage.title)
    val title: Flow<String?> = _title

    // Funkcja aktualizująca stan wskazujący obecną aktywność
    fun setCurrentScreen(screen: MenuItems){
        _currentScreen.value = screen
        _title.value = _currentScreen.value!!.title
    }

}