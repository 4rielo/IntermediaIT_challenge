package com.intermedia.challenge.data.services

import com.google.gson.annotations.SerializedName
import com.intermedia.challenge.data.models.Event
import retrofit2.Response
import retrofit2.http.*

interface EventsService {
    @GET("events")
    suspend fun getEvents(
        @QueryMap auth: HashMap<String, String>,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String
    ): Response<EventsResponse>

    @GET("events/{eventsID}/comics")
    suspend fun getEventComics(
        @Path("eventsID") eventsId: Int,
        @QueryMap auth: HashMap<String, String>,
    ): Response<ComicDataWrapper>
}

data class EventsResponse(
    val code: Int = 0,
    @SerializedName("data")
    val eventsList: EventDataContainer
)

data class EventDataContainer(
    @SerializedName("results")
    val eventsList: List<Event>
)
