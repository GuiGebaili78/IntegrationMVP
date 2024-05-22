package com.example.integrationmvp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

data class Item(val id: Long, val name: String)

interface ApiService {
    @GET("api/paciente")
    suspend fun getItems(): List<Item>

    @POST("api/paciente")
    suspend fun createItem(@Body item: Item): Item

    @PUT("api/paciente/{id}")
    suspend fun updateItem(@Path("id") id: Long, @Body item: Item): Item

    @DELETE("api/paciente/{id}")
    suspend fun deleteItem(@Path("id") id: Long)
}

object ApiClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://sua-api.com/") // Substitua pela URL da sua API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
