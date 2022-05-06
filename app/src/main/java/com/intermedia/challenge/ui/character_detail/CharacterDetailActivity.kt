package com.intermedia.challenge.ui.character_detail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.databinding.ActivityCharacterDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailActivity:  AppCompatActivity() {

    companion object {
        internal const val SELECTED_CHARACTER = "selected_character"
    }

    private lateinit var binding: ActivityCharacterDetailBinding
    private val characterDetailViewModel: CharacterDetailViewModel by viewModel()

    private lateinit var characterAppearancesAdapter: CharacterAppearancesAdapter
    private var characterDetail: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.let {
            characterDetail = it.get(SELECTED_CHARACTER) as? Character
        }
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterAppearancesAdapter = CharacterAppearancesAdapter()
        binding.apply {
            viewModel = characterDetailViewModel
            character = characterDetail
            rvAppearances.adapter = characterAppearancesAdapter
        }

        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.support_action_bar)
            val characterName = findViewById<AppCompatTextView>(R.id.tvHeaderTitle)
            characterName.text = characterDetail?.name
            val closeButton = findViewById<AppCompatImageView>(R.id.ivCloseButton)
            closeButton.setOnClickListener {
                finish()
            }
        }
        characterDetailViewModel.fetchComics(characterDetail?.id ?:0)
        setObservers()
    }

    private fun setObservers() {
        observeComicsList()
        observeLoadingStatus()
    }

    private fun observeComicsList() {
        characterDetailViewModel.characterComics.observe(this) {
            it?.let {
                characterAppearancesAdapter.addAll(it)
            }
        }
    }

    private fun observeLoadingStatus() {
        characterDetailViewModel.isLoading.observe(this) {
            binding.apply {
                pbIsLoading.isVisible = it
                tvIsLoading.isVisible = it
            }
        }
    }
}