package hu.bme.kocsis.richard.animelist.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import hu.bme.kocsis.richard.animelist.model.AnimeDto

@Entity(tableName = "animes_table",
        indices = [Index(value = ["title"], unique = true)])

data class Anime(
        @PrimaryKey
        val title: String,
        val original_title: String,
        val original_title_romanised: String,
        val description: String,
        val director: String,
        val producer: String,
        val score: Double,
        val release_date: String,
        val favourite: Int
) {
    constructor(animeDto: AnimeDto) : this(
            animeDto.title,
            animeDto.originalTitle,
            animeDto.originalTitleRomanised,
            animeDto.description,
            animeDto.director,
            animeDto.producer,
            animeDto.score,
            animeDto.releaseDate,
            if (animeDto.favourite) 1 else 0
    )
}