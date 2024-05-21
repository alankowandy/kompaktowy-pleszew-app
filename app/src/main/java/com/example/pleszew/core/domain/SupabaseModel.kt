package com.example.pleszew.core.domain

import com.example.pleszew.miejsca_rozrywki.data.MiejscaRozrywkiRepository
import com.example.pleszew.miejsca_rozrywki.data.MiejscaRozrywkiRepositoryImpl
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
    fun provideMiejscaRozrywkiRepository(postgrest: Postgrest): MiejscaRozrywkiRepository{
        return MiejscaRozrywkiRepositoryImpl(postgrest)
    }
}