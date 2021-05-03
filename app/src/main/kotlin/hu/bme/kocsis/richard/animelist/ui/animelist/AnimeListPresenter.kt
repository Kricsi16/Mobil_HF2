package hu.bme.kocsis.richard.animelist.ui.animelist

import hu.bme.kocsis.richard.animelist.data.Anime
import hu.bme.kocsis.richard.animelist.interactor.animelist.FilmsInteractor
import hu.bme.kocsis.richard.animelist.interactor.animelist.event.GetFilmsEvent
import hu.bme.kocsis.richard.animelist.model.AnimeDto
import hu.bme.kocsis.richard.animelist.model.Film
import hu.bme.kocsis.richard.animelist.repository.AnimeDataSource
import hu.bme.kocsis.richard.animelist.repository.AnimeRepository
import hu.bme.kocsis.richard.animelist.ui.Presenter
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class AnimeListPresenter @Inject constructor(
        private val executor: Executor,
        private val filmsInteractor: FilmsInteractor,
        private val animeRepository: AnimeDataSource
) : Presenter<AnimeListScreen>() {

    override fun attachScreen(screen: AnimeListScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun refreshFilms() {
        executor.execute {
            filmsInteractor.getFilms()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetFilmsEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.films != null) {
                    screen?.updateAnimeList((event.films as MutableList<Film>).map { AnimeDto(it) })
                }

            }
        }
    }
}
