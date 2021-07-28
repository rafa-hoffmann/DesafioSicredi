package com.example.desafiosicredi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiosicredi.ui.main.EventsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EventsFragment.newInstance())
                .commitNow()
        }
    }
}