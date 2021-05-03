package hu.bme.kocsis.richard.animelist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.kocsis.richard.animelist.model.AnimeDto


@Dao
interface AnimeDao {
    fun insertAnimeList(animeList: List<AnimeDto>) {
        animeList.forEach {
            val entity = Anime(it)
            addAnime(entity)
        }

    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAnime(anime: Anime)

    @Query("SELECT * FROM animes_table")
    fun getAnimes(): LiveData<List<Anime>>

    @Query("SELECT COUNT(*) FROM animes_table")
    fun getAnimeCount(): LiveData<Int>

    @Query("UPDATE animes_table SET favourite=:favourite WHERE title=:title")
    fun updateFavourite(favourite: Boolean, title: String)

    @Query("SELECT * FROM animes_table WHERE title=:title")
    fun getDetails(title: String): LiveData<Anime>

    @Query("SELECT * FROM animes_table WHERE favourite=1")
    fun getFavourites(): LiveData<List<Anime>>
}