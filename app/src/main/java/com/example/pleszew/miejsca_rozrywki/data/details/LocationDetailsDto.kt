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
    val locationPhone: String?,

    @SerialName("adres_email")
    val locationEmail: String?,

//    @SerialName("ulica")
//    val locationStreet: String,
//
//    @SerialName("numer_domu")
//    val locationStreetNum: String?,
//
//    @SerialName("numer_mieszkania")
//    val locationHouseNum: String?,
//
//    @SerialName("kod_pocztowy")
//    val locationPostal: String,
//
//    @SerialName("miejscowosc")
//    val location: String,
//
    @SerialName("nazwa_www")
    val locationWebsiteName: List<String>?,
//
    @SerialName("adres_www")
    val locationWebsite: List<String>?,
)