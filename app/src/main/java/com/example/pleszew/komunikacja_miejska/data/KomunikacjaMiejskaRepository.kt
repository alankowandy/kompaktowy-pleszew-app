package com.example.pleszew.komunikacja_miejska.data

import com.example.pleszew.komunikacja_miejska.data.start.StopsDto
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

interface KomunikacjaMiejskaRepository{
    suspend fun getStops(): List<StopsDto>
}

class KomunikacjaMiejskaRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
): KomunikacjaMiejskaRepository {
    override suspend fun getStops(): List<StopsDto> {
        TODO("Not yet implemented")
    }
}