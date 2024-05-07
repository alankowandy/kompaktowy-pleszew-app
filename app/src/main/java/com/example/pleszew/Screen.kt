package com.example.pleszew

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.dp

sealed class Screen(
    val title: String,
    val route: String
) {
    sealed class DrawerScreen(
        val dTtitle: String,
        val dRoute: String,
        @DrawableRes val icon: Int
    ): Screen(dTtitle, dRoute){

        object HomeView: DrawerScreen(
            "KOMPAKTOWY PLESZEW",
            "home_view",
            R.drawable.ic_home_24
        )

        object WywozSmieci: DrawerScreen(
            "WYWÓZ ŚMIECI",
            "wywoz_smieci",
            R.drawable.ic_recycling
        )

        object KomunikacjaMiejska: DrawerScreen(
            "KOMUNIKACJA MIEJSKA",
            "komunikacja_miejska",
            R.drawable.ic_bus
        )

        object WydarzeniaKulturalne: DrawerScreen(
            "WYDARZENIA KULTURALNE",
            "wydarzenia_kulturalne",
            R.drawable.ic_ticket_96
        )

        object MiejscaRozrywki: DrawerScreen(
            "MIEJSCA ROZRYWKI",
            "miejsca_rozrywki",
            R.drawable.ic_bowling
        )

        object MiastoSamorzad: DrawerScreen(
            "MIASTO I SAMORZĄD",
            "miasto_samorzad",
            R.drawable.ic_tower
        )

        object Miasto15: DrawerScreen(
            "MIASTO 15'",
            "miasto15",
            R.drawable.ic_stopwatch
        )
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.HomeView,
    Screen.DrawerScreen.WywozSmieci,
    Screen.DrawerScreen.KomunikacjaMiejska,
    Screen.DrawerScreen.WydarzeniaKulturalne,
    Screen.DrawerScreen.MiejscaRozrywki,
    Screen.DrawerScreen.MiastoSamorzad,
    Screen.DrawerScreen.Miasto15
)