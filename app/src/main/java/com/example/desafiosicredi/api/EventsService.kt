package com.example.desafiosicredi.api

import com.example.desafiosicredi.model.Checkin
import com.example.desafiosicredi.model.Event
import com.google.gson.JsonElement
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsService {

    @GET("events")
    suspend fun getEvents(): List<Event>

    @GET("events/{id}")
    suspend fun getEvent(@Path("id") eventId: Int): Event

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("checkin")
    suspend fun checkIn(@Body checkin: Checkin): JsonElement
}