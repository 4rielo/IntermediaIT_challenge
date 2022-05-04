package com.intermedia.challenge.data.repositories

import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.services.EventsResponse
import com.intermedia.challenge.data.services.EventsService

class EventsRepository(
    private val eventsService: EventsService
): BaseRepository() {

    suspend fun getEvents(offset: Int, limit: Int = 25, orderBy: String = "startDate"): NetResult<EventsResponse> =
        handleResult(eventsService.getEvents(authParams.getMap(), offset, limit, orderBy))
}