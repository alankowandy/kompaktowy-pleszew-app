package com.example.pleszew.komunikacja_miejska.data.start

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SelectedStopDto (

    @SerialName("id")
    val id: Int,

    @SerialName("nazwa_przystanku")
    val stopName: String,

    @SerialName("czasy_odjazdu")
    val departureTimes: List<String>
)