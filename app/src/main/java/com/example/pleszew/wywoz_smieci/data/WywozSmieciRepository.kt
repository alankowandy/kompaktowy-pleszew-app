package com.example.pleszew.wywoz_smieci.data

import com.example.pleszew.miejsca_rozrywki.data.details.LocationDetailsDto
import com.example.pleszew.wywoz_smieci.data.details.GarbageCollectionDetailsDto
import com.example.pleszew.wywoz_smieci.data.main.GarbageCollectionDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.rpc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

interface WywozSmieciRepository {
    suspend fun getGarbageCollection(): List<GarbageCollectionDto>

    suspend fun getGarbageCollectionDetails(id: String, month: String): List<GarbageCollectionDetailsDto>
}

class WywozSmieciRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
): WywozSmieciRepository {
    override suspend fun getGarbageCollection(): List<GarbageCollectionDto> {
        return withContext(Dispatchers.IO) {
            val data = postgrest.rpc("get_trasy_details")
                .decodeList<GarbageCollectionDto>()
            data
        }
    }

    override suspend fun getGarbageCollectionDetails(id: String, month: String): List<GarbageCollectionDetailsDto> {
        return withContext(Dispatchers.IO) {
            val data = postgrest.rpc(
                function = "get_wywoz_details",
                parameters = buildJsonObject {
                    put("p_id_trasy", id)
                    put("p_miesiac", month)
                }
            ).decodeList<GarbageCollectionDetailsDto>()
            data
        }
    }
}