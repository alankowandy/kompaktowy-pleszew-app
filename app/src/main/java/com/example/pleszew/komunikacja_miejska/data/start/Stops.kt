package com.example.pleszew.komunikacja_miejska.data.start

data class Stops (
    val id: String,
    val stopName: String
) {
    fun doesMarchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf<String>(
            stopName,
            "${stopName.first()}"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = false)
        }
    }
}