package com.example.pleszew.komunikacja_miejska.data

import com.example.pleszew.komunikacja_miejska.data.start.SearchedStopsDto
import com.example.pleszew.komunikacja_miejska.data.start.SelectedStopDto
import com.example.pleszew.komunikacja_miejska.data.start.StopsDto
import com.example.pleszew.miasto_samorzad.data.OfficeDto
import com.example.pleszew.miejsca_rozrywki.data.details.LocationDetailsDto
import com.example.pleszew.wywoz_smieci.data.details.GarbageCollectionDetailsDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.rpc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

interface KomunikacjaMiejskaRepository{
    suspend fun getStops(): List<StopsDto>

    suspend fun getStopsSearched(searchText: String): List<StopsDto>

    suspend fun getLines(stopId: String): List<SearchedStopsDto>

    suspend fun getSelectedStop(stopName: String, lineId: String): List<SelectedStopDto>
}

class KomunikacjaMiejskaRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
): KomunikacjaMiejskaRepository {
    override suspend fun getStops(): List<StopsDto> {
        return withContext(Dispatchers.IO) {
            val data = postgrest.rpc("get_przystanki_details")
                .decodeList<StopsDto>()
            data
        }
    }

    override suspend fun getStopsSearched(searchText: String): List<StopsDto> {
        return withContext(Dispatchers.IO) {
            val data = postgrest.rpc(
                function = "search_przystanki",
                parameters = buildJsonObject {
                    put("p_search_string", searchText)
                }
            ).decodeList<StopsDto>()
            data
        }
    }

    override suspend fun getLines(stopId: String): List<SearchedStopsDto> {
        return withContext(Dispatchers.IO) {
            val data = postgrest.rpc(
                function = "search_trasy_by_przystanek_kolejnosc",
                parameters = buildJsonObject {
                    put("p_search_number", stopId)
                }
            ).decodeList<SearchedStopsDto>()
            data
        }
    }

    override suspend fun getSelectedStop(stopName: String, lineId: String): List<SelectedStopDto> {
        return withContext(Dispatchers.IO) {
            val data = postgrest.rpc(
                function = "search_przystanek_and_czas_odjazdu_by_nazwa_linii",
                parameters = buildJsonObject {
                    put("p_nazwa_przystanku", stopName)
                    put("p_id_linii", lineId)
                }
            ).decodeList<SelectedStopDto>()
            data
        }
    }
}