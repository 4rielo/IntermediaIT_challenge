package com.intermedia.challenge.ui.character_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.data.models.Comic
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.CharactersRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {

    private val _characterComics = MutableLiveData<List<Comic>?>()
    val characterComics: LiveData<List<Comic>?> get() = _characterComics

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _connectionError = MutableLiveData(false)
    val connectionError: LiveData<Boolean> = _connectionError

    fun fetchComics(characterId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                when (val response = charactersRepository.getCharacterComics(characterId)) {
                    is NetResult.Success -> {
                        _characterComics.value = response.data.comicsList?.listOfComics
                        _connectionError.value = false
                    }
                    is NetResult.Error -> {
                        _connectionError.value = true
                    }
                }
            } catch (e:Exception) {
                _connectionError.value = true
            }
            _isLoading.value = false
        }
    }

}