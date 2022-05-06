package com.intermedia.challenge.data.models

data class Comic(
    val title: String?,
    val issueNumber: Double?,
    val dates: List<ComicDate>?
)

data class ComicDate(
    val type: String?,
    val date: String?
    )
