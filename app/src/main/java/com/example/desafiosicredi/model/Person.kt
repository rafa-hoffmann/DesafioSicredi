package com.example.desafiosicredi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("eventId")
    val eventId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("picture")
    val picture: String?
) : Parcelable