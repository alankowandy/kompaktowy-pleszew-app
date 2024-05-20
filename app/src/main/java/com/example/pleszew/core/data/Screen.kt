package com.example.pleszew.core.data

sealed class Screen(
    val title: String,
    val route: String
) {
    data object HomePage: Screen(
        title = "KOMPAKTOWY PLESZEW",
        route = "home_page"
    )

    data object WywozSmieci: Screen(
        title = "WYWÓZ ŚMIECI",
        route = "wywoz_smieci"
    )

    data object WywozSmieciStart: Screen(
        title = "WYWÓZ ŚMIECI",
        route = "wywoz_smieci_start"
    )

    data object WywozSmieciDetails: Screen(
        title = "WYWÓZ ŚMIECI",
        route = "wywoz_smieci_details"
    )

    data object KomunikacjaMiejska: Screen(
        title = "KOMUNIKACJA MIEJSKA",
        route = "komunikacja_miejska"
    )

    data object KomunikacjaMiejskaStart: Screen(
        title = "KOMUNIKACJA MIEJSKA",
        route = "komunikacja_miejska_start"
    )

    data object KomunikacjaMiejskaDetails: Screen(
        title = "KOMUNIKACJA MIEJSKA",
        route = "komunikacja_miejska_details"
    )

    data object WydarzeniaKulturalne: Screen(
        title = "WYDARZENIA KULTURALNE",
        route = "wydarzenia_kulturalne"
    )

    data object WydarzeniaKulturalneStart: Screen(
        title = "WYDARZENIA KULTURALNE",
        route = "wydarzenia_kulturalne_start"
    )

    data object WydarzeniaKulturalneDetails: Screen(
        title = "WYDARZENIA KULTURALNE",
        route = "wydarzenia_kulturalne_details"
    )

    data object MiejscaRozrywki: Screen(
        title = "MIEJSCA ROZRYWKI",
        route = "miejsca_rozrywki"
    )

    data object MiejscaRozrywkiStart: Screen(
        title = "MIEJSCA ROZRYWKI",
        route = "miejsca_rozrywki_start"
    )

    data object MiejscaRozrywkiDetails: Screen(
        title = "MIEJSCA ROZRYWKI",
        route = "miejsca_rozrywki_details"
    )

    data object MiastoSamorzad: Screen(
        title = "MIASTO I SAMORZĄD",
        route = "miasto_samorzad"
    )

    data object Miasto15: Screen(
        title = "MIASTO 15'",
        route = "miasto15"
    )

}