package hu.bme.kocsis.richard.animelist.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Film(
        /**
         * Unique identifier representing a specific film
         */
        @SerializedName("id")
        var id: String = "",

        /**
         * Title of the film
         */
        @SerializedName("title")
        var title: String = "",

        /**
         * Original title of the film
         */
        @SerializedName("original_title")
        var originalTitle: String = "",

        /**
         * Orignal title in romanised form
         */
        @SerializedName("original_title_romanised")
        var originalTitleRomanised: String = "",

        /**
         * Description of the film
         */
        @SerializedName("description")
        var description: String = "",

        /**
         * Director of the film
         */
        @SerializedName("director")
        var director: String = "",

        /**
         * Producer of the film
         */
        @SerializedName("producer")
        var producer: String = "",

        /**
         * Release year of film
         */
        @SerializedName("release_date")
        var releaseDate: String = "",

        /**
         * Running time of the film in minutes
         */
        @SerializedName("running_time")
        var runningTime: String = "",

        /**
         * Rotten Tomato score of film
         */
        @SerializedName("rt_score")
        var rtScore: String = "",

        /**
         * People found in film
         */
        @SerializedName("people")
        var people: List<String> = ArrayList(),

        /**
         * Species found in film
         */
        @SerializedName("species")
        var species: List<String> = ArrayList(),

        /**
         * Locations found in film
         */
        @SerializedName("locations")
        var locations: List<String> = ArrayList(),

        /**
         * Vehicles found in film
         */
        @SerializedName("vehicles")
        var vehicles: List<String> = ArrayList(),

        /**
         * URL of film
         */
        @SerializedName("url")
        var url: String? = null
)
