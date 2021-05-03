package hu.bme.kocsis.richard.animelist.interactor.animelist.event

import hu.bme.kocsis.richard.animelist.model.Film

data class GetFilmsEvent(
        var code: Int = 0,
        var films: List<Film>? = null,
        var throwable: Throwable? = null
)
