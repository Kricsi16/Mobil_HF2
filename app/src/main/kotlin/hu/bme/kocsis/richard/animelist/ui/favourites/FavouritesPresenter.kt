package hu.bme.kocsis.richard.animelist.ui.favourites

import hu.bme.kocsis.richard.animelist.data.Anime
import hu.bme.kocsis.richard.animelist.interactor.animelist.FilmsInteractor
import hu.bme.kocsis.richard.animelist.interactor.animelist.event.GetDetailsEvent
import hu.bme.kocsis.richard.animelist.interactor.animelist.event.GetFavouritesEvent
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

class FavouritesPresenter @Inject constructor(
        private val executor: Executor,
        private val filmsInteractor: FilmsInteractor,
        private val animeRepository: AnimeDataSource
) : Presenter<FavouritesScreen>() {

    override fun attachScreen(screen: FavouritesScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetFavouritesEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.favourites!=null) {
                    screen?.updateFavourites()
                }

            }
        }
    }
}