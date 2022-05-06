package com.intermedia.challenge.data.models

data class Event(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val resourceURI: String = "",
    val urls: List<Url> = listOf(),
    val thumbnail: Thumbnail = Thumbnail(),
    val comics: Appearances = Appearances(),
    val stories: Appearances = Appearances(),
    val series: Appearances = Appearances(),
    val characters: CharacterList = CharacterList(),
    val start: String = "",
    val end: String = "",
)

data class CharacterList(
    val available: Int = 0,
    val returned: Int = 0,
    val collectionURI: String = "",
    val items: List<CharacterSummary> = listOf()
)

data class CharacterSummary (
    val resourceURI: String = "",
    val name : String = "",
    val role : String = ""
)