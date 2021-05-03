package hu.bme.kocsis.richard.animelist.repository

import androidx.lifecycle.LiveData
import hu.bme.kocsis.richard.animelist.data.Anime

interface AnimeRepository {

    fun addAnime(anime: Anime)

    fun getAnimes(): LiveData<List<Anime>>

    fun updateFavourite(favourite: Boolean, title: String)

    fun getDetails(title: String): LiveData<Anime>

    fun getFavourites(): LiveData<List<Anime>>
}