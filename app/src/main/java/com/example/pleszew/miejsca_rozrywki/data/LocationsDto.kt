package com.example.pleszew.miejsca_rozrywki.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationsDto(
    @SerialName("id")
    val id: Int,

    @SerialName("nazwa_miejsca")
    val name: String
)