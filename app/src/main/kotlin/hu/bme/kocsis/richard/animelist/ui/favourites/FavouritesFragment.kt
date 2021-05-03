package hu.bme.kocsis.richard.animelist.ui.favourites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.kocsis.richard.animelist.R
import hu.bme.kocsis.richard.animelist.injector
import hu.bme.kocsis.richard.animelist.model.AnimeDto
import hu.bme.kocsis.richard.animelist.repository.AnimeDataSource
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class FavouritesFragment : Fragment(), FavouritesScreen{

    private val displayedArtists: MutableList<AnimeDto> = mutableListOf()
    private var favouritesAdapter: FavouritesAdapter? = null
    private lateinit var recyclerViewFavourites: RecyclerView


    @Inject
    lateinit var favouritesPresenter: FavouritesPresenter

    @Inject
    lateinit var animeRepository: AnimeDataSource


    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        favouritesPresenter.attachScreen(this)
    }

    override fun onDetach() {
        favouritesPresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val llm = LinearLayoutManager(context)

        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerViewFavourites = view.findViewById<RecyclerView>(R.id.recyclerViewFavourites)
        recyclerViewFavourites.layoutManager = llm

        favouritesAdapter = FavouritesAdapter(context!!, displayedArtists)
        recyclerViewFavourites.adapter = favouritesAdapter

        updateFavourites()

    }

    override fun showFavourites(favourites: List<AnimeDto>){
        favouritesAdapter?.initList(favourites)
        favouritesAdapter?.notifyDataSetChanged()
    }

    override fun updateFavourites() {
        val self = viewLifecycleOwner
        runBlocking {
           }
            animeRepository.getFavourites().observe(self, Observer {
                val favouritesList = it.map { AnimeDto(it) }
                showFavourites(favouritesList)

            })
        }

    override fun showNetworkError(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(): FavouritesFragment {
            return FavouritesFragment()
        }
    }

}