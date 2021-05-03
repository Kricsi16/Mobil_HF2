package hu.bme.kocsis.richard.animelist.interactor.animelist.event

import hu.bme.kocsis.richard.animelist.model.AnimeDto

data class GetDetailsEvent(
        var code: Int = 0,
        var anime: AnimeDto? = null,
        var throwable: Throwable? = null
)
