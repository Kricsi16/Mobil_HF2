package hu.bme.kocsis.richard.animelist.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.kocsis.richard.animelist.data.Anime
import hu.bme.kocsis.richard.animelist.data.AnimeDao

@Database(entities = [Anime::class], version = 1)
abstract class AnimeListDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao

}