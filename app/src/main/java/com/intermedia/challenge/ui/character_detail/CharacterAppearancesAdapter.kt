package com.intermedia.challenge.ui.character_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.ui.base.BaseAdapter
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Comic
import com.intermedia.challenge.databinding.ViewCharacterAppearancesItemBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CharacterAppearancesAdapter : BaseAdapter<Comic, CharacterAppearancesAdapter.CharacterAppearancesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAppearancesViewHolder =
        CharacterAppearancesViewHolder(
            ViewCharacterAppearancesItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_character_appearances_item,
                    parent,
                    false
                )
            ), onClickListener
        )

    override fun onBindViewHolder(holder: CharacterAppearancesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class CharacterAppearancesViewHolder(
        private val binding: ViewCharacterAppearancesItemBinding,
        private val onClickListener: ((Comic) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Comic) = with(itemView) {
            binding.apply {
                appearance = item
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ")
                item.dates?.first()?.date?.let { it ->
                    val dateValue = dateFormat.parse(it)
                    val outputDate: DateFormat = DateFormat.getDateInstance(DateFormat.YEAR_FIELD)
                    dateValue?.let { date ->
                        tvAppearanceDate.text = outputDate.format(date).takeLast(4)
                    }
                }

            }
        }
    }
}