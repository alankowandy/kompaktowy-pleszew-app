package com.example.pleszew.wywoz_smieci.data.main

data class GarbageCollection(
    val id: String,
    val name: String,
    val month: List<String>,
    val routeName: String,
    val route: String
)