package hu.bme.kocsis.richard.animelist.interactor.animelist

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import hu.bme.kocsis.richard.animelist.interactor.animelist.event.GetDetailsEvent
import hu.bme.kocsis.richard.animelist.interactor.animelist.event.GetFilmsEvent
import hu.bme.kocsis.richard.animelist.model.AnimeDto
import hu.bme.kocsis.richard.animelist.network.FilmsApi
import hu.bme.kocsis.richard.animelist.repository.AnimeDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class FilmsInteractor @Inject constructor(private var filmsApi: FilmsApi) {

    fun getFilms() {
        val event = GetFilmsEvent()

        try {
            val artistsQueryCall = filmsApi.getFilms()

            val response = artistsQueryCall.execute()
            Log.d("Reponse", response.body().toString())
            if (response.code() != 200) {
                throw Exception("Result code is not 200")
            }
            event.code = response.code()
            event.films = response.body() ?: emptyList()
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }



}
