package hu.bme.kocsis.richard.animelist.interactor.animelist.event

import hu.bme.kocsis.richard.animelist.data.Anime
import hu.bme.kocsis.richard.animelist.model.AnimeDto
import hu.bme.kocsis.richard.animelist.model.Film

data class GetFavouritesEvent(
        var code: Int = 0,
        var favourites: List<Film>? = null,
        var throwable: Throwable? = null
)


