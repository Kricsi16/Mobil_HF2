package hu.bme.kocsis.richard.animelist.ui.details

import androidx.lifecycle.LifecycleOwner
import hu.bme.kocsis.richard.animelist.interactor.animelist.FilmsInteractor
import hu.bme.kocsis.richard.animelist.interactor.animelist.event.GetDetailsEvent
import hu.bme.kocsis.richard.animelist.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val executor: Executor, private val filmsInteractor: FilmsInteractor) : Presenter<DetailsScreen>() {

    override fun attachScreen(screen: DetailsScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetDetailsEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.anime!=null) {
                    screen?.showDetails(event.anime!!)
                }

            }
        }
    }
}