package hu.bme.kocsis.richard.animelist.ui.favourites

import hu.bme.kocsis.richard.animelist.model.AnimeDto

interface FavouritesScreen {
    fun updateFavourites()
    fun showFavourites(favourites: List<AnimeDto>)
    fun showNetworkError(errorMsg: String)
}