package com.example.integrationmvp.api

import com.example.integrationmvp.model.ConsultaModel
import com.example.integrationmvp.model.ProntuarioModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ProntuarioApiService {
    @GET("api/Prontuario")
    suspend fun getProntuarios(): List<ProntuarioModel>

    @Headers("Content-Type: application/json")
    @POST("api/Prontuario")
    suspend fun createProntuario(@Body prontuario: ProntuarioModel): ProntuarioModel

    @Headers("Content-Type: application/json")
    @PUT("api/Prontuario/{id}")
    suspend fun updateProntuario(@Path("id") id: Long, @Body prontuario: ProntuarioModel): ProntuarioModel

    @Headers("Content-Type: application/json")
    @GET("api/Prontuario/{id}")
    suspend fun getProntuario(@Path("id") id: Long): ProntuarioModel

    @DELETE("api/Prontuario/{id}")
    suspend fun deleteProntuario(@Path("id") id: Long)
}

object ProntuarioApiClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5041/") // Substitua pela URL da sua API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ProntuarioApiService by lazy {
        retrofit.create(ProntuarioApiService::class.java)
    }
}
