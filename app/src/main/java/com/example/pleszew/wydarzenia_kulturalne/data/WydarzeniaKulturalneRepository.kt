package com.example.pleszew.wydarzenia_kulturalne.data

import com.example.pleszew.miasto_samorzad.data.OfficeDto
import com.example.pleszew.miejsca_rozrywki.data.details.LocationDetailsDto
import com.example.pleszew.wydarzenia_kulturalne.data.details.EventDetailsDto
import com.example.pleszew.wydarzenia_kulturalne.data.main.EventDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.rpc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

interface WydarzeniaKulturalneRepository {
    suspend fun getEvents(): List<EventDto>

    suspend fun getEventDetails(id: String): List<EventDetailsDto>
}

class WydarzeniaKulturalneRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
): WydarzeniaKulturalneRepository {
    override suspend fun getEvents(): List<EventDto> {
        return withContext(Dispatchers.IO) {
            val data = postgrest.rpc("get_wydarzenia")
                .decodeList<EventDto>()
            data
        }
    }

    override suspend fun getEventDetails(id: String): List<EventDetailsDto> {
        return withContext(Dispatchers.IO) {
            val data = postgrest.rpc(
                function = "get_wydarzenie_details",
                parameters = buildJsonObject {
                    put("event_id", id)
                }
            ).decodeList<EventDetailsDto>()
            data
        }
    }
}