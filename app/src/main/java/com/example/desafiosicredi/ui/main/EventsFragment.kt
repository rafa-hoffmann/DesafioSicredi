package com.example.desafiosicredi.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiosicredi.databinding.EventsFragmentBinding
import com.example.desafiosicredi.model.Checkin
import com.example.desafiosicredi.model.Response
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : Fragment() {

    companion object {
        fun newInstance() = EventsFragment()
    }

    private val viewModel: EventsViewModel by viewModel()
    private lateinit var binding: EventsFragmentBinding
    private lateinit var eventsAdapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EventsFragmentBinding.inflate(inflater, container, false)

        val click: (Checkin) -> Unit = { checkIn ->
            when(checkIn.validity()) {
                Response.Valid -> {
                    viewModel.viewModelScope.launch {
                        viewModel.tryCheckIn(checkIn)
                    }
                }
                Response.InvalidEventID -> {
                    Toast.makeText(requireContext(), "ID do evento inválido", Toast.LENGTH_LONG).show()
                }
                Response.InvalidName -> {
                    Toast.makeText(requireContext(), "Você precisa informar um nome", Toast.LENGTH_LONG).show()
                }
                Response.InvalidEmail -> {
                    Toast.makeText(requireContext(), "Você precisa informar um email", Toast.LENGTH_LONG).show()
                }
            }
        }
        eventsAdapter = EventsAdapter(click)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = eventsAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.viewModelScope.launch {
            viewModel.getEvents()
        }

        viewModel.events.observe(viewLifecycleOwner, {
            eventsAdapter.submitList(it)
        })

        viewModel.apiError.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.checkInSuccess.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(requireContext(), "Check-in concluído com sucesso!", Toast.LENGTH_LONG).show()
            }
        })
    }
}