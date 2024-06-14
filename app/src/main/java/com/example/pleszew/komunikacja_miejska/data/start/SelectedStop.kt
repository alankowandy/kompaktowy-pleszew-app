package com.example.pleszew.komunikacja_miejska.data.start

data class SelectedStop (
    val id: String,
    val stopName: String,
    val departureTimes: List<String>
)