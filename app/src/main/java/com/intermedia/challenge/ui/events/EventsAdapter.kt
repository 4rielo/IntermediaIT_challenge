package com.intermedia.challenge.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.ui.base.BaseAdapter
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Comic
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.databinding.ViewEventItemBinding
import com.intermedia.challenge.ui.character_detail.CharacterAppearancesAdapter

class EventsAdapter : BaseAdapter<Pair<Event, List<Comic>?>, EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder =
        EventsViewHolder(
            ViewEventItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_event_item,
                    parent,
                    false
                )
            ), onClickListener
        )

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class EventsViewHolder(
        private val binding: ViewEventItemBinding,
        private val onClickListener: ((Pair<Event, List<Comic>?>) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        private val detailAdapter = CharacterAppearancesAdapter()

        fun bind(item: Pair<Event, List<Comic>?>) = with(itemView) {
            binding.apply {
                event = item.first
                ivExpand.setOnClickListener {
                    clDetailSection.isVisible = !clDetailSection.isVisible
                    it.setBackgroundResource(
                        if(clDetailSection.isVisible) {
                            R.drawable.ic_baseline_keyboard_arrow_up
                        } else {
                            R.drawable.ic_baseline_keyboard_arrow_down
                        }
                    )
                }
                rvDetails.adapter = detailAdapter
            }
            detailAdapter.addAll(item.second.orEmpty())
        }
    }

}