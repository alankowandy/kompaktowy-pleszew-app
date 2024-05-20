package com.example.pleszew.core.data

import androidx.annotation.DrawableRes
import com.example.pleszew.R

//sealed class Screen(
//    val title: String,
//    val route: String
//) {
//    sealed class DrawerScreen(
//        val dTitle: String,
//        val dRoute: String,
//        @DrawableRes val icon: Int
//    ): Screen(dTitle, dRoute){
//
//        object HomeView: DrawerScreen(
//            "KOMPAKTOWY PLESZEW",
//            "home_view",
//            R.drawable.ic_home_24
//        )
//
//        object WywozSmieci: DrawerScreen(
//            "WYWÓZ ŚMIECI",
//            "wywoz_smieci",
//            R.drawable.ic_recycling_24
//        )
//
//        object KomunikacjaMiejska: DrawerScreen(
//            "KOMUNIKACJA MIEJSKA",
//            "komunikacja_miejska",
//            R.drawable.ic_bus
//        )
//
//        object WydarzeniaKulturalne: DrawerScreen(
//            "WYDARZENIA KULTURALNE",
//            "wydarzenia_kulturalne",
//            R.drawable.ic_ticket_96
//        )
//
//        object MiejscaRozrywki: DrawerScreen(
//            "MIEJSCA ROZRYWKI",
//            "miejsca_rozrywki",
//            R.drawable.ic_bowling
//        )
//
//        object MiastoSamorzad: DrawerScreen(
//            "MIASTO I SAMORZĄD",
//            "miasto_samorzad",
//            R.drawable.ic_tower
//        )
//
//        object Miasto15: DrawerScreen(
//            "MIASTO 15'",
//            "miasto15",
//            R.drawable.ic_stopwatch
//        )
//    }
//}

sealed class DrawerScreen(
    val title: String,
    val route: String,
    @DrawableRes val icon: Int
) {

    object HomePage: DrawerScreen(
        "KOMPAKTOWY PLESZEW",
        "home_page",
        R.drawable.ic_home_24
    )

    object WywozSmieci: DrawerScreen(
        "WYWÓZ ŚMIECI",
        "wywoz_smieci",
        R.drawable.ic_recycling_24
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

val screensInDrawer = listOf(
    DrawerScreen.HomePage,
    DrawerScreen.WywozSmieci,
    DrawerScreen.KomunikacjaMiejska,
    DrawerScreen.WydarzeniaKulturalne,
    DrawerScreen.MiejscaRozrywki,
    DrawerScreen.MiastoSamorzad,
    DrawerScreen.Miasto15
)