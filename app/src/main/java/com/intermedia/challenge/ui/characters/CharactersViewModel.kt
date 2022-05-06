package com.intermedia.challenge.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.CharactersRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _connectionError = MutableLiveData(false)
    val connectionError: LiveData<Boolean> = _connectionError

    private var _offset: Int = 0

    init {
        loadCharacters(0)
    }

    fun loadCharacters(offset: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                when (val response = charactersRepository.getCharacters(offset)) {
                    is NetResult.Success -> {
                        _characters.postValue(response.data.charactersList.characters)
                        _connectionError.value = false
                        _offset = offset.plus(15)
                    }
                    is NetResult.Error -> {
                        _connectionError.value = true
                    }
                }
            } catch (e:Exception) {
                _connectionError.value = true
            }

            _isLoading.postValue(false)
        }
    }

    fun loadMoreCharacters() {
        loadCharacters(_offset)
    }
}