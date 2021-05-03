package hu.bme.kocsis.richard.animelist.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import hu.bme.kocsis.richard.animelist.R
import hu.bme.kocsis.richard.animelist.injector
import hu.bme.kocsis.richard.animelist.model.AnimeDto
import hu.bme.kocsis.richard.animelist.repository.AnimeDataSource
import hu.bme.kocsis.richard.animelist.ui.animelist.AnimeListActivity
import hu.bme.kocsis.richard.animelist.ui.favourites.FavouritesActivity
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsScreen {

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    @Inject
    lateinit var animeRepository: AnimeDataSource


    var title: String? = null

    lateinit var title_text: TextView
    lateinit var original_title_text: TextView
    lateinit var director_text: TextView
    lateinit var producer_text: TextView
    lateinit var release_date_text: TextView
    lateinit var score_text: TextView
    lateinit var description_text: TextView

    private lateinit var toolBar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)



        title = intent.getStringExtra("Title")

        title_text = findViewById(R.id.title)
        original_title_text = findViewById(R.id.original_title)
        director_text = findViewById(R.id.director)
        producer_text = findViewById(R.id.producer)
        release_date_text = findViewById(R.id.release_date)
        score_text = findViewById(R.id.score)
        description_text = findViewById(R.id.description)


        injector.inject(this)
    }


    override fun showDetails(details: AnimeDto) {
        title_text.text = details.title
        original_title_text.text = details.originalTitle
        director_text.text = details.director
        producer_text.text = details.producer
        release_date_text.text = details.releaseDate
        score_text.text = details.score.toString()
        description_text.text = details.description

    }

    override fun showNetworkError(errorMsg: String) {

        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        detailsPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        detailsPresenter.detachScreen()
    }

    override fun onResume() {
        super.onResume()
        val self = this
        if (title != null) {
            animeRepository.getDetails(title!!).observe(self, Observer {
                showDetails(AnimeDto(it))
            })
        }
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
                val openAnimeListActivity = Intent(this, AnimeListActivity::class.java)
                openAnimeListActivity.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                startActivityIfNeeded(openAnimeListActivity, 0)
                true}
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