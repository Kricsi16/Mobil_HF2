package hu.bme.kocsis.richard.animelist.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.kocsis.richard.animelist.data.AnimeDao
import hu.bme.kocsis.richard.animelist.interactor.animelist.FilmsInteractor
import hu.bme.kocsis.richard.animelist.repository.AnimeDataSource
import hu.bme.kocsis.richard.animelist.repository.AnimeRepository
import hu.bme.kocsis.richard.animelist.ui.animelist.AnimeListPresenter
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun artistsPresenter(executor: Executor, filmsInteractor: FilmsInteractor, animeRepository: AnimeDataSource) = AnimeListPresenter(executor, filmsInteractor, animeRepository)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}
