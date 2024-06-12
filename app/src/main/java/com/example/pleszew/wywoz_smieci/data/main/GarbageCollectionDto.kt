package com.example.pleszew.wywoz_smieci.data.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GarbageCollectionDto(

    @SerialName("id_trasy")
    val id: String,

    @SerialName("miasto")
    val name: String,

    @SerialName("miesiac")
    val month: List<String>?,

    @SerialName("nazwa_trasy")
    val routeName: String,

    @SerialName("przebieg_trasy")
    val route: String
)