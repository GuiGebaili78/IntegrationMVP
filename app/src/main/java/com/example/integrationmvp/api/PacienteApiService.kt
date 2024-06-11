package com.example.integrationmvp.api

import com.example.integrationmvp.model.PacienteModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import okhttp3.OkHttpClient


interface PacienteApiService {
    @GET("api/Paciente")
    suspend fun getPacientes(): List<PacienteModel>

    @Headers("Content-Type: application/json")
    @POST("api/Paciente")
    suspend fun createPaciente(@Body paciente: PacienteModel): PacienteModel

    @Headers("Content-Type: application/json")
    @PUT("api/Paciente/{id}")
    suspend fun updatePaciente(@Path("id") id: Long, @Body paciente: PacienteModel): PacienteModel

    @Headers("Content-Type: application/json")
    @GET("api/Paciente/{id}")
    suspend fun getPaciente(@Path("id") id: Long): PacienteModel

    @DELETE("api/Paciente/{id}")
    suspend fun deletePaciente(@Path("id") id: Long)
}

object PacienteApiClient {
    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5041/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: PacienteApiService by lazy {
        retrofit.create(PacienteApiService::class.java)
    }
}