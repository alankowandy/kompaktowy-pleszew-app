package com.example.pleszew.wywoz_smieci.data.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GarbageCollectionDetailsDto(
    @SerialName("miasto")
    val name: String,

    @SerialName("przebieg_trasy")
    val route: String,

    @SerialName("nazwa_trasy")
    val routeName: String,

    @SerialName("rodzaj_odpadow")
    val garbageType: String,

    @SerialName("dzien_wywozu")
    val collectionDate: String
)