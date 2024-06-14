package com.example.pleszew.komunikacja_miejska.data.start

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedStopsDto (

    @SerialName("id_linii")
    val lineId: String,

    @SerialName("kolejnosc_przystankow")
    val stopOrder: String,

    @SerialName("kierunek")
    val direction: String,

    @SerialName("przystanki_nazwy")
    val stopsOrdered: List<String>
)
