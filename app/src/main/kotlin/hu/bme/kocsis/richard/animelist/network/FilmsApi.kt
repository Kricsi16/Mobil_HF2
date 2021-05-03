package hu.bme.kocsis.richard.animelist.network

import hu.bme.kocsis.richard.animelist.model.Film
import retrofit2.Call
import retrofit2.http.GET

interface FilmsApi {
    @GET("films")
    fun getFilms(): Call<List<Film>>
}
