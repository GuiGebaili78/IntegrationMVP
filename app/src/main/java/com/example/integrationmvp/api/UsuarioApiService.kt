package com.example.integrationmvp.api

import com.example.integrationmvp.model.PacienteModel
import com.example.integrationmvp.model.UsuarioModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface UsuarioApiService {
    @GET("api/Usuario")
    suspend fun getUsuarios(): List<UsuarioModel>

    @Headers("Content-Type: application/json")
    @POST("api/Usuario")
    suspend fun createUsuario(@Body usuario: UsuarioModel): UsuarioModel

    @Headers("Content-Type: application/json")
    @PUT("api/Usuario/{id}")
    suspend fun updateUsuario(@Path("id") id: Long, @Body usuario: UsuarioModel): UsuarioModel

    @Headers("Content-Type: application/json")
    @GET("api/Usuario/{id}")
    suspend fun getUsuario(@Path("id") id: Long): UsuarioModel


    @DELETE("api/Usuario/{id}")
    suspend fun deleteUsuario(@Path("id") id: Long)
}

object UsuarioApiClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5041/") // Substitua pela URL da sua API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: UsuarioApiService by lazy {
        retrofit.create(UsuarioApiService::class.java)
    }
}
