package hu.bme.kocsis.richard.animelist.ui.favourites

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import hu.bme.kocsis.richard.animelist.R
import hu.bme.kocsis.richard.animelist.ui.animelist.AnimeListActivity


class FavouritesActivity : AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_favourites)
            supportFragmentManager.beginTransaction().replace(R.id.fragment, FavouritesFragment.newInstance()).commit()


        }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_animelist -> {
                val openAnimeListActivity = Intent(this, AnimeListActivity::class.java)
                openAnimeListActivity.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivityIfNeeded(openAnimeListActivity, 0)
                true}
            R.id.action_favourites ->{true}
            else -> super.onOptionsItemSelected(item)
        }
    }
    }
