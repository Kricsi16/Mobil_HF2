package hu.bme.kocsis.richard.animelist

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import hu.bme.kocsis.richard.animelist.data.AnimeDao
import hu.bme.kocsis.richard.animelist.repository.AnimeDataSource
import hu.bme.kocsis.richard.animelist.repository.AnimeListDatabase
import hu.bme.kocsis.richard.animelist.repository.AnimeRepository
import javax.inject.Singleton

@Module
class RoomModule(private val context: Context) {
    private val database = Room.databaseBuilder(context, AnimeListDatabase::class.java, "anime_database").allowMainThreadQueries().build()

    @Provides
    fun context() = context

    @Singleton
    @Provides
    fun database() = database

    @Singleton
    @Provides
    fun animeDao(database: AnimeListDatabase) = database.animeDao()

    @Singleton
    @Provides
    fun repository(animeDao: AnimeDao) = AnimeDataSource(animeDao)

}