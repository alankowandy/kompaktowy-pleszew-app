package com.example.pleszew.miasto_samorzad.data

data class Office (
    val officeId: String,
    val officeName: String,
    val officePhoneNumber: String,
    val officeEmail: String?,
    val officeWebsite: String?,
    val officeStreet: String,
    val officeHouseNumber: String,
    val officeApartmentNumber: String?,
    val officePostal: String,
    val officeTown: String,
    val officeLatitude: Double,
    val officeLongitude: Double
)