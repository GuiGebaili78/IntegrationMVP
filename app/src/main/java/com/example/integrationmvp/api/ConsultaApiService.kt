package com.example.integrationmvp.api

import com.example.integrationmvp.model.ConsultaModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ConsultaApiService {
    @GET("api/Consulta")
    suspend fun getConsultas(): List<ConsultaModel>

    @Headers("Content-Type: application/json")
    @POST("api/Consulta")
    suspend fun createConsulta(@Body consulta: ConsultaModel): ConsultaModel

    @Headers("Content-Type: application/json")
    @PUT("api/Consulta/{id}")
    suspend fun updateConsulta(@Path("id") id: Long, @Body consulta: ConsultaModel): ConsultaModel

    @DELETE("api/Consulta/{id}")
    suspend fun deleteConsulta(@Path("id") id: Long)
}

object ConsultaApiClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5041/") // Substitua pela URL da sua API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ConsultaApiService by lazy {
        retrofit.create(ConsultaApiService::class.java)
    }
}
