package com.example.pleszew.core.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pleszew.core.data.DrawerScreen


class SharedViewModel: ViewModel() {

    // Dynamiczny stan zmieniający się względem aktywności na której jesteśmy
    private val _currentScreen: MutableState<DrawerScreen> = mutableStateOf(DrawerScreen.HomePage)

    val currentScreen: MutableState<DrawerScreen>
        get() = _currentScreen

    // Funkcja aktualizująca stan wskazujący obecną aktywność
    fun setCurrentScreen(screen: DrawerScreen){
        _currentScreen.value = screen
    }


}