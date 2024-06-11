package com.example.pleszew.wydarzenia_kulturalne.data.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDetailsDto (
    @SerialName("nazwa_wydarzenia")
    val eventName: String,

    @SerialName("data_wydarzenia")
    val eventDate: String,

    @SerialName("g_rozpoczecia")
    val eventStart: String,

    @SerialName("g_zakonczenia")
    val eventEnd: String,

    @SerialName("grafika")
    val eventImage: String,

    @SerialName("ulica")
    val eventStreet: String,

    @SerialName("numer_domu")
    val eventStreetNum: String,

    @SerialName("numer_lokalu")
    val eventApartmentNum: String,

    @SerialName("szczegoly")
    val eventDetails: String,

    @SerialName("kod_pocztowy")
    val eventPostal: String,

    @SerialName("miejscowosc")
    val eventTown: String
)