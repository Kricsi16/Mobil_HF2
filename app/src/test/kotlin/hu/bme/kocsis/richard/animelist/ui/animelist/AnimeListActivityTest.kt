package hu.bme.kocsis.richard.animelist.ui.animelist

import android.R
import android.view.View
import androidx.test.core.app.ActivityScenario.launch
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController


@RunWith(RobolectricTestRunner::class)
//@Config(constants = BuildConfig::class)
class AnimeListActivityTest {
    private lateinit var animeListActivity: AnimeListActivity
    private lateinit var animeListController: ActivityController<AnimeListActivity>

    @Before
    @Throws(Exception::class)
    fun setup() {
// Calls the lifecycle: create-start-postCreate-resume
        animeListController = Robolectric.buildActivity(AnimeListActivity::class.java)
        animeListActivity = animeListController.get()


    }

    @Test
    @Throws(java.lang.Exception::class)
    fun shouldHaveAnimeListFragment() {
    }

    @Test
    @Throws(Exception::class)
    fun testDetailsButtonIsDisplayed() {


    }
}