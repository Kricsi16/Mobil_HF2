package hu.bme.kocsis.richard.animelist.model

import hu.bme.kocsis.richard.animelist.data.Anime
import kotlin.math.roundToInt

data class AnimeDto constructor(
    val title: String,
    val originalTitle: String,
    val originalTitleRomanised: String,
    val description: String,
    val director: String,
    val producer: String,
    val score: Double,
    val releaseDate: String,
    var favourite: Boolean
) {
    constructor(f: Film) : this(
            f.title,
            f.originalTitle,
            f.originalTitleRomanised,
            f.description,
            f.director,
            f.producer,
            f.rtScore.toDouble(),
            f.releaseDate,
            false
    )

    constructor(a: Anime) : this(
            a.title,
            a.original_title,
            a.original_title_romanised,
            a.description,
            a.director,
            a.producer,
            a.score,
            a.release_date,
            a.favourite == 1
    )
}