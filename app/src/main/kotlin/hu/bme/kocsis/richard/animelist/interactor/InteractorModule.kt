package hu.bme.kocsis.richard.animelist.interactor

import dagger.Module
import dagger.Provides
import hu.bme.kocsis.richard.animelist.data.AnimeDao
import hu.bme.kocsis.richard.animelist.interactor.animelist.FilmsInteractor
import hu.bme.kocsis.richard.animelist.network.FilmsApi
import hu.bme.kocsis.richard.animelist.repository.AnimeDataSource
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideFilmsInteractor(filmsApi: FilmsApi) = FilmsInteractor(filmsApi)
}
