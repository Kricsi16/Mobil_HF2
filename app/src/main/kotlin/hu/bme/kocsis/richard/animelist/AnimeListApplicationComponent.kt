package hu.bme.kocsis.richard.animelist

import android.app.Application
import dagger.Component
import hu.bme.kocsis.richard.animelist.data.AnimeDao
import hu.bme.kocsis.richard.animelist.interactor.InteractorModule
import hu.bme.kocsis.richard.animelist.network.NetworkModule
import hu.bme.kocsis.richard.animelist.repository.AnimeListDatabase
import hu.bme.kocsis.richard.animelist.repository.AnimeRepository
import hu.bme.kocsis.richard.animelist.ui.UIModule
import hu.bme.kocsis.richard.animelist.ui.animelist.AnimeListFragment
import hu.bme.kocsis.richard.animelist.ui.details.DetailsActivity
import hu.bme.kocsis.richard.animelist.ui.favourites.FavouritesActivity
import hu.bme.kocsis.richard.animelist.ui.favourites.FavouritesFragment

import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class, RoomModule::class])
interface AnimeListApplicationComponent {
    fun inject(animeListFragment: AnimeListFragment)
    fun inject(detailsActivity: DetailsActivity)
    fun inject(favouritesFragment: FavouritesFragment)
}
