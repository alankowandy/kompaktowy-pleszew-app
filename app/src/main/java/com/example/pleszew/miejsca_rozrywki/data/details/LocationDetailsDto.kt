package com.example.pleszew.miejsca_rozrywki.data.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Websites(
    @SerialName("nazwa_www")
    val locationWebsiteName: String,
//
    @SerialName("adres_www")
    val locationWebsite: String,
)

@Serializable
data class LocationDetailsDto(
    @SerialName("nazwa_miejsca")
    val locationName: String,

    @SerialName("numer_tel")
    val locationPhone: String? = null,

    @SerialName("adres_email")
    val locationEmail: String? = null,

    @SerialName("ulica")
    val locationStreet: String,

    @SerialName("numer_domu")
    val locationStreetNum: String? = null,

    @SerialName("numer_mieszkania")
    val locationHouseNum: String? = null,

    @SerialName("kod_pocztowy")
    val locationPostal: String,

    @SerialName("miejscowosc")
    val location: String,

    @SerialName("dzien_tygodnia")
    val weekDay: List<String>? = emptyList(),

    @SerialName("g_otwarcia")
    val openHours: List<String>? = emptyList(),

    @SerialName("g_zamkniecia")
    val closeHours: List<String>? = emptyList(),

    @SerialName("nazwa_www")
    val locationWebsiteName: List<String>? = emptyList(),

    @SerialName("adres_www")
    val locationWebsite: List<String>? = emptyList(),
)