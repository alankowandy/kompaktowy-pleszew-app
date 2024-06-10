package com.example.pleszew.miasto_samorzad.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfficeDto (
    @SerialName("id")
    val officeId: String,

    @SerialName("nazwa_urzedu")
    val officeName: String,

    @SerialName("numer_telefonu")
    val officePhoneNumber: String,

    @SerialName("adres_email")
    val officeEmail: String?,

    @SerialName("adres_www")
    val officeWebsite: String?,

    @SerialName("ulica")
    val officeStreet: String,

    @SerialName("numer_domu")
    val officeHouseNumber: String,

    @SerialName("numer_lokalu")
    val officeApartmentNumber: String?,

    @SerialName("kod_pocztowy")
    val officePostal: String,

    @SerialName("miejscowosc")
    val officeTown: String
)