package hu.bme.kocsis.richard.animelist.ui.animelist

import hu.bme.kocsis.richard.animelist.model.AnimeDto

interface AnimeListScreen {
    fun updateAnimeList(toSave: List<AnimeDto>)
    fun showNetworkError(errorMsg: String)
}
