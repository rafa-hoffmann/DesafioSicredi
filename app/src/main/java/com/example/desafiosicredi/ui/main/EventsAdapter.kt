package com.example.desafiosicredi.ui.main

import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiosicredi.R
import com.example.desafiosicredi.databinding.EventListItemBinding
import com.example.desafiosicredi.model.Checkin
import com.example.desafiosicredi.model.Event
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EventsAdapter(private val click: (Checkin) -> Unit) :
    ListAdapter<Event, EventsAdapter.EventsViewHolder>(EventsAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(getItem(position), click)
    }

    class EventsViewHolder private constructor(private val eventListItem: EventListItemBinding) :
        RecyclerView.ViewHolder(eventListItem.root) {
        fun bind(data: Event, openDetail: ((Checkin) -> Unit)) {
            eventListItem.apply {
                eventCheckIn.setOnClickListener {
                    eventCheckInForm.visibility = if (eventCheckInForm.visibility == View.VISIBLE) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }
                }

                eventCheckInFinish.setOnClickListener {
                    openDetail.invoke(
                        Checkin(
                            data.id,
                            name.editText?.text?.toString(),
                            email.editText?.text?.toString()
                        )
                    )
                }

                data.image?.let {
                    Picasso.get().load(it).into(eventImage, object : Callback {
                        override fun onSuccess() {
                            eventImage.visibility = View.VISIBLE
                        }

                        override fun onError(e: Exception?) {
                            eventImage.visibility = View.GONE
                        }
                    })
                }
                eventName.text = data.title

                data.date?.let {
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val dateString = dateFormat.format(Date(it * 1000))
                    eventDate.text = dateString
                } ?: run {
                    eventDate.visibility = View.GONE
                }

                eventPrice.text = data.price.toString()

                data.longitude?.let { longitude ->
                    data.latitude?.let { latitude ->
                        val geocoder = Geocoder(itemView.context, Locale.getDefault())

                        val result = geocoder.getFromLocation(latitude, longitude, 1)

                        result.firstOrNull()?.let {
                            eventLocale.text = it.getAddressLine(0)
                        }
                    }
                }
                eventDescription.text = data.description
            }
        }

        companion object {
            fun from(parent: ViewGroup): EventsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val eventListItem = EventListItemBinding.inflate(layoutInflater, parent, false)
                return EventsViewHolder(eventListItem)
            }
        }
    }

    private companion object : DiffUtil.ItemCallback<Event>() {

        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }
}