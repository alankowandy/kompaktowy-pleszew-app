package com.example.pleszew.core.domain

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Navigation {
    val route: String
}

object HomePage: Navigation {
    override val route = "home_page"
}

object WywozSmieci: Navigation {
    override val route = "wywoz_smieci"
}

object WywozSmieciStart: Navigation {
    override val route = "wywoz_smieci_start"
}

object WywozSmieciDetails: Navigation {
    override val route = "wywoz_smieci_details"
}

object KomunikacjaMiejska: Navigation {
    override val route = "komunikacja_miejska"
}

object KomunikacjaMiejskaStart: Navigation {
    override val route = "komunikacja_miejska_start"
}

object KomunikacjaMiejskaDetails: Navigation {
    override val route = "komunikacja_miejska_details"
}

object WydarzeniaKulturalne: Navigation {
    override val route = "wydarzenia_kulturalne"
}

object WydarzeniaKulturalneStart: Navigation {
    override val route = "wydarzenia_kulturalne_start"
}

object WydarzeniaKulturalneDetails: Navigation {
    override val route = "wydarzenia_kulturalne_details"
}

object MiejscaRozrywki: Navigation {
    override val route = "miejsca_rozrywki"
}

object MiejscaRozrywkiStart: Navigation {
    override val route = "miejsca_rozrywki_start"
}

object MiejscaRozrywkiDetails: Navigation {
    override val route = "miejsca_rozrywki_details"
    const val locationId = "location_id"
    val arguments = listOf(navArgument(name = locationId) {
        type = NavType.StringType
    })
    fun createRouteWithParam(locationId: String) = "$route/${locationId}"
}

object MiastoSamorzad: Navigation {
    override val route = "miasto_samorzad"
}

object Miasto15: Navigation {
    override val route = "miasto15"
}