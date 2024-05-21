package com.example.pleszew.core.data

import androidx.annotation.DrawableRes
import com.example.pleszew.R

sealed class MenuItems(
    val title: String,
    val route: String,
    @DrawableRes val icon: Int
) {

    data object HomePage: MenuItems(
        title = "KOMPAKTOWY PLESZEW",
        route = "home_page",
        icon = R.drawable.ic_home_24
    )

    data object WywozSmieci: MenuItems(
        title = "WYWÓZ ŚMIECI",
        route = "wywoz_smieci",
        icon = R.drawable.ic_recycling_24
    )

    data object KomunikacjaMiejska: MenuItems(
        title = "KOMUNIKACJA MIEJSKA",
        route = "komunikacja_miejska",
        icon = R.drawable.ic_bus
    )

    data object WydarzeniaKulturalne: MenuItems(
        title = "WYDARZENIA KULTURALNE",
        route = "wydarzenia_kulturalne",
        icon = R.drawable.ic_ticket_96
    )

    data object MiejscaRozrywki: MenuItems(
        title = "MIEJSCA ROZRYWKI",
        route = "miejsca_rozrywki",
        icon = R.drawable.ic_bowling
    )

    data object MiastoSamorzad: MenuItems(
        title = "MIASTO I SAMORZĄD",
        route = "miasto_samorzad",
        icon = R.drawable.ic_tower
    )

    data object Miasto15: MenuItems(
        title = "MIASTO 15'",
        route = "miasto15",
        icon = R.drawable.ic_stopwatch
    )
}

val screensInDrawer = listOf(
    MenuItems.HomePage,
    MenuItems.WywozSmieci,
    MenuItems.KomunikacjaMiejska,
    MenuItems.WydarzeniaKulturalne,
    MenuItems.MiejscaRozrywki,
    MenuItems.MiastoSamorzad,
    MenuItems.Miasto15
)

val screensInMenu = listOf(
    MenuItems.WywozSmieci,
    MenuItems.KomunikacjaMiejska,
    MenuItems.WydarzeniaKulturalne,
    MenuItems.MiejscaRozrywki,
    MenuItems.MiastoSamorzad,
    MenuItems.Miasto15
)