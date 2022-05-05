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

    fun fetchComics(characterId: Int) {
        viewModelScope.launch {
            when (val response = charactersRepository.getCharacterComics(characterId)) {
                is NetResult.Success -> {
                    _characterComics.value = response.data.comicsList?.listOfComics
                }
                is NetResult.Error -> {
                    // TODO complete
                }
            }
        }
    }

}