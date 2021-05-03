package hu.bme.kocsis.richard.animelist.repository


import androidx.lifecycle.LiveData
import hu.bme.kocsis.richard.animelist.data.Anime
import hu.bme.kocsis.richard.animelist.data.AnimeDao
import javax.inject.Inject


class AnimeDataSource @Inject constructor(private val animeDao: AnimeDao) : AnimeRepository {

    override fun addAnime(anime: Anime) {
        return animeDao.addAnime(anime)
    }

    override fun getAnimes(): LiveData<List<Anime>> {
        return animeDao.getAnimes()
    }

    override fun updateFavourite(favourite: Boolean, title: String) {
        return animeDao.updateFavourite(favourite, title)
    }

    override fun getDetails(title: String): LiveData<Anime> {
        return animeDao.getDetails(title)
    }

    override fun getFavourites(): LiveData<List<Anime>> {
        return animeDao.getFavourites()
    }

}