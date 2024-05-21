package com.example.pleszew.miejsca_rozrywki.data

import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MiejscaRozrywkiRepository {
    suspend fun getLocations(): List<LocationsDto>

    //suspend fun getLocationDetails(): List<LocationDetailsDto>
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

//    override suspend fun getLocationDetails(): List<LocationDetailsDto> {
//        return withContext(Dispatchers.IO) {
//
//        }
//    }
}