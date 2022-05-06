package com.intermedia.challenge.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.databinding.FragmentEventsBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val eventsViewModel: EventsViewModel by sharedViewModel()
    private val adapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = eventsViewModel
            srSwipeToRefresh.setOnRefreshListener {
                srSwipeToRefresh.isRefreshing = true
                eventsViewModel.loadEvents()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventsList()
    }

    private fun setupEventsList() {
        binding.listEvents.adapter = adapter
        eventsViewModel.eventsAndComics.observe(viewLifecycleOwner) {
            adapter.addAll(it)
            binding.srSwipeToRefresh.isRefreshing = false
        }
    }
}