package hu.bme.kocsis.richard.animelist.ui.animelist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import hu.bme.kocsis.richard.animelist.R
import hu.bme.kocsis.richard.animelist.ui.favourites.FavouritesActivity
import kotlinx.android.synthetic.main.activity_anime_list.*

class AnimeListActivity : AppCompatActivity() {

    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_list)
        //setSupportActionBar(toolbar)

        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        toolBar.inflateMenu(R.menu.menu_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment, AnimeListFragment.newInstance()).commit()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {

            R.id.action_animelist -> {
                true
            }
            R.id.action_favourites -> {
                val openFavouritesActivity = Intent(this, FavouritesActivity::class.java)
                openFavouritesActivity.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivityIfNeeded(openFavouritesActivity, 0)
                true


            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
