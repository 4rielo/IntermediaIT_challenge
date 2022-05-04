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
    private val viewModel: EventsViewModel by sharedViewModel()
    private val adapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventsList()
        setupPagination()
    }

    private fun setupPagination() {
        binding.listEvents.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreEvents()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun setupEventsList() {
        adapter.onClickListener = { character ->
            // TODO complete
        }
        binding.listEvents.adapter = adapter
        viewModel.events.observe(viewLifecycleOwner) { events ->
            adapter.addAll(events)
        }
    }
}