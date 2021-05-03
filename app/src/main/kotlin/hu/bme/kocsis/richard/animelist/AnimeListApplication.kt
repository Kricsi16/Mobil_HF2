package hu.bme.kocsis.richard.animelist

import android.app.Application
import hu.bme.kocsis.richard.animelist.ui.UIModule

class AnimeListApplication : Application() {
    lateinit var injector: AnimeListApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAnimeListApplicationComponent.builder().uIModule(UIModule(this)).roomModule(RoomModule(this)).build()
    }
}
