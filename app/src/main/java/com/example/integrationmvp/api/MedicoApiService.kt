package com.example.integrationmvp.api

import com.example.integrationmvp.model.MedicoModel
import com.example.integrationmvp.model.PacienteModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface MedicoApiService {
    @GET("api/Medico")
    suspend fun getMedicos(): List<MedicoModel>

    @Headers("Content-Type: application/json")
    @POST("api/Medico")
    suspend fun createMedico(@Body paciente: MedicoModel): MedicoModel

    @Headers("Content-Type: application/json")
    @PUT("api/Medico/{id}")
    suspend fun updateMedico(@Path("id") id: Long, @Body paciente: MedicoModel): MedicoModel

    @DELETE("api/Medico/{id}")
    suspend fun deleteMedico(@Path("id") id: Long)
}

object MedicoApiClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5041/") // Substitua pela URL da sua API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: MedicoApiService by lazy {
        retrofit.create(MedicoApiService::class.java)
    }
}
