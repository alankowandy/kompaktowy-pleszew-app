package com.example.pleszew.komunikacja_miejska.data.start

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StopsDto (

    @SerialName("id")
    val id: String,

    @SerialName("nazwa_przystanku")
    val stopName: String
)