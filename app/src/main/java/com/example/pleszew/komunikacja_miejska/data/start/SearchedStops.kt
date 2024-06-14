package com.example.pleszew.komunikacja_miejska.data.start

data class SearchedStops (
    val lineId: String,
    val stopOrder: String,
    val direction: String,
    val stopsOrdered: List<String>
)