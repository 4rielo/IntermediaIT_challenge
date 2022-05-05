package com.intermedia.challenge.di

import com.intermedia.challenge.ui.characters.CharactersViewModel
import com.intermedia.challenge.data.repositories.CharactersRepository
import com.intermedia.challenge.data.repositories.EventsRepository
import com.intermedia.challenge.ui.character_detail.CharacterDetailViewModel
import com.intermedia.challenge.ui.events.EventsViewModel
import com.intermedia.challenge.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val businessModule = module {

    viewModel { CharactersViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { EventsViewModel(get()) }
    viewModel { MainViewModel() }

    single { CharactersRepository(get()) }
    single { EventsRepository(get()) }
}