package com.example.pleszew.core.domain

import com.example.pleszew.miasto_samorzad.data.MiastoSamorzadRepository
import com.example.pleszew.miasto_samorzad.data.MiastoSamorzadRepositoryImpl
import com.example.pleszew.miejsca_rozrywki.data.start.MiejscaRozrywkiRepository
import com.example.pleszew.miejsca_rozrywki.data.start.MiejscaRozrywkiRepositoryImpl
import com.example.pleszew.wydarzenia_kulturalne.data.WydarzeniaKulturalneRepository
import com.example.pleszew.wydarzenia_kulturalne.data.WydarzeniaKulturalneRepositoryImpl
import com.example.pleszew.wywoz_smieci.data.WywozSmieciRepository
import com.example.pleszew.wywoz_smieci.data.WywozSmieciRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModel {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://oidxojajrzddqpbpnmrw.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9pZHhvamFqcnpkZHFwYnBubXJ3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ3NDkzNjMsImV4cCI6MjAzMDMyNTM2M30.k11eFtv1F46_oZS2eLPgFMFkUbdEUk1lH89Dnh-u_Ng"
        ) {
            install(Postgrest)
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }

    @Provides
    fun provideMiejscaRozrywkiRepository(postgrest: Postgrest): MiejscaRozrywkiRepository {
        return MiejscaRozrywkiRepositoryImpl(postgrest)
    }

    @Provides
    fun provideMiastoSamorzadRepository(postgrest: Postgrest): MiastoSamorzadRepository {
        return MiastoSamorzadRepositoryImpl(postgrest)
    }

    @Provides
    fun provideWydarzeniaKulturalneRepository(postgrest: Postgrest): WydarzeniaKulturalneRepository {
        return WydarzeniaKulturalneRepositoryImpl(postgrest)
    }

    @Provides
    fun provideWywozSmieciRepository(postgrest: Postgrest): WywozSmieciRepository {
        return WywozSmieciRepositoryImpl(postgrest)
    }
}