package com.example.pleszew.wydarzenia_kulturalne.data.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDto (
    @SerialName("id")
    val eventId: Int,

    @SerialName("nazwa_wydarzenia")
    val eventName: String,

    @SerialName("data_wydarzenia")
    val eventDate: String,

    @SerialName("g_rozpoczecia")
    val eventStart: String,

    @SerialName("g_zakonczenia")
    val eventEnd: String
)