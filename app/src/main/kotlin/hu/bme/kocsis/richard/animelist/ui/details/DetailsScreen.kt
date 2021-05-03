package hu.bme.kocsis.richard.animelist.ui.details

import hu.bme.kocsis.richard.animelist.model.AnimeDto


interface DetailsScreen {
    fun showDetails(details: AnimeDto)
    fun showNetworkError(errorMsg: String)
}