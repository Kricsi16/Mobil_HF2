package hu.bme.kocsis.richard.animelist.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.base.Predicates.equalTo
import hu.bme.kocsis.richard.animelist.data.Anime
import hu.bme.kocsis.richard.animelist.data.AnimeDao
import hu.bme.kocsis.richard.animelist.model.AnimeDto
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class AnimeListDatabaseTest {

    private lateinit var animeDao: AnimeDao
    private lateinit var db: AnimeListDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, AnimeListDatabase::class.java).build()
        animeDao = db.animeDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val anime = Anime(
             "alma",
             "alma",
             "alma",
            "alma",
             "alma",
            "alma",
                6.1,
            "alma",
            1
        )

        animeDao.addAnime(anime)


        val details = animeDao.getDetails(anime.title)

    }
}