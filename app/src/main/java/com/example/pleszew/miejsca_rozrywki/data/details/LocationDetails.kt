package com.example.pleszew.miejsca_rozrywki.data.details

data class LocationDetails(
    val locationName: String,
    val locationPhone: String?,
    val locationEmail: String?,
    val locationStreet: String,
    val locationStreetNum: String?,
    val locationHouseNum: String?,
    val locationPostal: String,
    val location: String,
    val weekDay: List<String>?,
    val openHours: List<String>?,
    val closeHours: List<String>?,
    val locationWebsiteName: List<String>?,
    val locationWebsite: List<String>?,
)
