package com.example.desafiosicredi.model

import com.google.gson.annotations.SerializedName

data class Checkin(
    @SerializedName("eventId")
    val eventId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?
) {
    fun validity(): Response {
        return when {
            name.isNullOrBlank() -> Response.InvalidName
            email.isNullOrBlank() -> Response.InvalidEmail
            eventId == null -> Response.InvalidEventID
            else -> Response.Valid
        }
    }
}

enum class Response {
    Valid,
    InvalidName,
    InvalidEmail,
    InvalidEventID
}