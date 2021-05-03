package hu.bme.kocsis.richard.animelist

import android.app.Activity
import androidx.fragment.app.Fragment


val Activity.injector: AnimeListApplicationComponent
    get() {
        return (this.applicationContext as AnimeListApplication).injector
    }

val Fragment.injector: AnimeListApplicationComponent
    get() {
        return (this.requireContext().applicationContext as AnimeListApplication).injector
    }

