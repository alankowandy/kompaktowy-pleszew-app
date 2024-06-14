package com.example.pleszew.miasto_samorzad.data

import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.rpc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MiastoSamorzadRepository {
    suspend fun getOfficeDetails(): List<OfficeDto>
}

class MiastoSamorzadRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
): MiastoSamorzadRepository {
    override suspend fun getOfficeDetails(): List<OfficeDto> {
        return withContext(Dispatchers.IO) {
            val data = postgrest.rpc("get_urzad_details")
                .decodeList<OfficeDto>()
            data
        }
    }
}