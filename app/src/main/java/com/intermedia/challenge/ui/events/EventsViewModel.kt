package com.intermedia.challenge.ui.events

import androidx.lifecycle.*
import com.intermedia.challenge.data.models.Comic
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.EventsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class EventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {

    private val _eventsAndComics = MutableLiveData<List<Pair<Event, List<Comic>?>>>()
    val eventsAndComics: LiveData<List<Pair<Event, List<Comic>?>>> = _eventsAndComics

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _connectionError = MutableLiveData<Boolean>(false)
    val connectionError: LiveData<Boolean> = _connectionError

    init {
        loadEvents()
    }

    fun loadEvents(offset: Int = 0) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                when (val response = eventsRepository.getEvents(offset)) {
                    is NetResult.Success -> {
                        val events = response.data.eventsList.eventsList
                        val eventsAndComics: MutableList<Pair<Event, List<Comic>?>> = mutableListOf()
                        events.forEach {  event ->
                            val response = eventsRepository.getEventComics(event.id)
                            when (response) {
                                is NetResult.Success -> {
                                    eventsAndComics.add(event to response.data.comicsList?.listOfComics)
                                    _connectionError.value = false
                                }
                                is NetResult.Error -> {
                                    eventsAndComics.add(event to emptyList<Comic>())
                                }
                            }
                        }
                        _eventsAndComics.value = eventsAndComics
                    }
                    is NetResult.Error -> {
                        _connectionError.value = true
                    }
                }
            } catch (e: Exception) {
                _connectionError.value = true
            }
            _isLoading.value = false
        }
    }

}