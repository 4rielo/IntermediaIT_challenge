package com.intermedia.challenge.data.services

import com.google.gson.annotations.SerializedName
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.data.models.Comic
import kotlinx.android.parcel.Parcelize
import retrofit2.Response
import retrofit2.http.*

interface CharacterService {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap auth: HashMap<String, String>,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<CharactersResponse>

    @GET("characters/{characterID}/comics")
    suspend fun getCharacterComics(
        @Path("characterID") characterId: Int,
        @QueryMap auth: HashMap<String, String>,
    ): Response<ComicDataWrapper>
}

data class CharactersResponse(
    val code: Int = 0,
    @SerializedName("data")
    val charactersList: CharactersList
)

data class CharactersList(
    @SerializedName("results")
    val characters: List<Character>
)

data class ComicDataWrapper(
    @SerializedName("data")
    val comicsList: ComicsList?
)


data class ComicsList(
    @SerializedName("results")
    val listOfComics: List<Comic>?
)