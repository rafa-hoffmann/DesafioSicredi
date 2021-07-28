package com.example.desafiosicredi.model



import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("date")
    val date: Long?,
    @SerializedName("people")
    val people: List<Person>?
) : Parcelable
