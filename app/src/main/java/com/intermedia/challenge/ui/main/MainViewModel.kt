package com.intermedia.challenge.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intermedia.challenge.data.models.Character


class MainViewModel() : ViewModel() {

    private val _goToCharacterDetail = MutableLiveData<Character>()
    val goToCharacterDetail: LiveData<Character> = _goToCharacterDetail

    fun showCharacterDetailOf(character: Character) {
        _goToCharacterDetail.postValue(character)
    }
}