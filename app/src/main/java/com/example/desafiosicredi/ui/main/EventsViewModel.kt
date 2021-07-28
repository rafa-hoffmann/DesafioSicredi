package com.example.desafiosicredi.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiosicredi.api.EventsService
import com.example.desafiosicredi.model.Checkin
import com.example.desafiosicredi.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.dsl.module

val eventsViewModel = module {
    factory { EventsViewModel(get()) }
}

class EventsViewModel(private val eventsService: EventsService) : ViewModel() {

    val apiError = MutableLiveData<String>()
    val events = MutableLiveData<List<Event>>()
    val checkInSuccess = MutableLiveData<Boolean>()

    suspend fun getEvents() = withContext(Dispatchers.IO) {
        try {
            events.postValue(eventsService.getEvents())
        } catch (e: Exception) {
            apiError.postValue(e.localizedMessage)
        }
    }

    suspend fun tryCheckIn(checkin: Checkin) = withContext(Dispatchers.IO) {
        try {
            eventsService.checkIn(checkin)
            checkInSuccess.postValue(true)
        } catch (e: Exception) {
            apiError.postValue(e.localizedMessage)
        }
    }
}