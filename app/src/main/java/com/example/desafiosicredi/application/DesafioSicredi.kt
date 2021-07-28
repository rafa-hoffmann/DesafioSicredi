package com.example.desafiosicredi.application

import android.app.Application
import com.example.desafiosicredi.api.retrofitModule
import com.example.desafiosicredi.ui.main.eventsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DesafioSicredi : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DesafioSicredi)
            modules(listOf(retrofitModule, eventsViewModel))
        }
    }
}