package com.intermedia.challenge.data.repositories

import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.services.CharacterService
import com.intermedia.challenge.data.services.CharactersResponse
import com.intermedia.challenge.data.services.ComicDataWrapper
import retrofit2.Response

class CharactersRepository(
    private val characterService: CharacterService
): BaseRepository() {

    suspend fun getCharacters(offset: Int, limit: Int = 15): NetResult<CharactersResponse> =
        handleResult(characterService.getCharacters(authParams.getMap(), offset, limit))

    suspend fun getCharacterComics(characterId: Int): NetResult<ComicDataWrapper> =
        handleResult(characterService.getCharacterComics(characterId,authParams.getMap()))
}