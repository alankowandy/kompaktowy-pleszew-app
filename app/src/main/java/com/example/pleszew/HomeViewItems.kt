package com.example.pleszew

import androidx.annotation.DrawableRes

sealed class HomeViewItems(
    val name: String,
    @DrawableRes val icon: Int
) {
    object wywozSmieci: HomeViewItems(
        "Wywóz śmieci",
        R.drawable.ic_recycling_white
    )

    object komunikacjaMiejska: HomeViewItems(
        "Komunikacja miejska",
        R.drawable.ic_bus_white
    )

    object wydarzeniaKulturalne: HomeViewItems(
        "Wydarzenia kulturalen",
        R.drawable.ic_ticket_white
    )

    object miejscaRozrywki: HomeViewItems(
        "Miejsca rozrywki",
        R.drawable.ic_bowling_white
    )

    object miastoSamorzad: HomeViewItems(
        "Miasto i samorząd",
        R.drawable.ic_tower_white
    )

    object miasto15: HomeViewItems(
        "Miasto 15'",
        R.drawable.ic_stopwatch_white
    )
}