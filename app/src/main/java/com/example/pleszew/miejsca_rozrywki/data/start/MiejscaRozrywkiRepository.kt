package com.example.pleszew.miejsca_rozrywki.data.start

import com.example.pleszew.miejsca_rozrywki.data.details.LocationDetailsDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.rpc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

interface MiejscaRozrywkiRepository {
    suspend fun getLocations(): List<LocationsDto>

    suspend fun getDetails(id: String): List<LocationDetailsDto>
}

class MiejscaRozrywkiRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
): MiejscaRozrywkiRepository {
    override suspend fun getLocations(): List<LocationsDto> {
        return withContext(Dispatchers.IO) {
            val result = postgrest.from("miejsca_rozrywki_main")
                .select(
                    columns = Columns.list("id, nazwa_miejsca")
                ).decodeList<LocationsDto>()
            result
        }
    }

    override suspend fun getDetails(id: String): List<LocationDetailsDto> {
        return withContext(Dispatchers.IO) {
            val columns =
                Columns.raw("""
                    nazwa_miejsca,
                    numer_tel,
                    adres_email,
                    miejsca_rozrywki_www (
                       nazwa_www,
                       adres_www
                    )
                """.trimIndent())
            val data = postgrest.rpc(
                function = "get_miejsce_rozrywki_details",
                parameters = buildJsonObject {
                    put("user_id", 1)
                }
            ).decodeList<LocationDetailsDto>()
//            val result = postgrest.from("miejsca_rozrywki_main")
//                .select(columns = columns) {
//                    filter {
//                        eq("id", id)
//                    }
//                }.decodeList<LocationDetailsDto>()
            data
        }
    }
}