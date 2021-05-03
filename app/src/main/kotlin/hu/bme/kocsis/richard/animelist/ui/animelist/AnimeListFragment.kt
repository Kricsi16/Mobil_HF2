package hu.bme.kocsis.richard.animelist.ui.animelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import hu.bme.kocsis.richard.animelist.R
import hu.bme.kocsis.richard.animelist.data.Anime
import hu.bme.kocsis.richard.animelist.injector
import hu.bme.kocsis.richard.animelist.model.AnimeDto
import hu.bme.kocsis.richard.animelist.repository.AnimeDataSource
import hu.bme.kocsis.richard.animelist.ui.utils.hide
import hu.bme.kocsis.richard.animelist.ui.utils.show
import kotlinx.android.synthetic.main.fragment_anime_list.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AnimeListFragment : Fragment(), AnimeListScreen, AnimeListAdapter.AnimeFavouriteListener {

    private val displayedArtists: MutableList<AnimeDto> = mutableListOf()
    private var animeListAdapter: AnimeListAdapter? = null
    private lateinit var recyclerViewAnimeList: RecyclerView
    private lateinit var swipeRefreshLayoutAnimeList: SwipeRefreshLayout


    @Inject
    lateinit var animeListPresenter: AnimeListPresenter

    @Inject
    lateinit var animeRepository: AnimeDataSource


    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        animeListPresenter.attachScreen(this)
    }

    override fun onDetach() {
        animeListPresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_anime_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val llm = LinearLayoutManager(context)




        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerViewAnimeList = view.findViewById<RecyclerView>(R.id.recyclerViewAnimeList)
        recyclerViewAnimeList.layoutManager = llm

        animeListAdapter = AnimeListAdapter(context!!, displayedArtists, this)
        recyclerViewAnimeList.adapter = animeListAdapter

        swipeRefreshLayoutAnimeList = view.findViewById(R.id.swipeRefreshLayoutAnimeList)
        swipeRefreshLayoutAnimeList.setOnRefreshListener {
            animeListPresenter.refreshFilms()
        }
    }

    override fun onResume() {
        super.onResume()
        animeListPresenter.refreshFilms()
    }

    fun refreshAnimeList(animeList: List<AnimeDto>) {
        swipeRefreshLayoutAnimeList.isRefreshing = false
        displayedArtists.clear()
        displayedArtists.addAll(animeList)
        animeListAdapter?.notifyDataSetChanged()

        if (displayedArtists.isEmpty()) {
            recyclerViewAnimeList.hide()
            tvEmpty.show()
        } else {
            recyclerViewAnimeList.show()
            tvEmpty.hide()
        }
    }



    override fun updateAnimeList(toSave: List<AnimeDto>) {
        val self = viewLifecycleOwner
        runBlocking {
            toSave.map { item ->
                async {
                    animeRepository.addAnime(Anime(item))
                }
            }.awaitAll()
            animeRepository.getAnimes().observe(self, Observer {
                val animeList = it.map { AnimeDto(it) }
                refreshAnimeList(animeList)
            })
        }
    }

    override fun showNetworkError(errorMsg: String) {
        swipeRefreshLayoutAnimeList.isRefreshing = false
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(): AnimeListFragment {
            return AnimeListFragment()
        }
    }

    override fun onFavouriteUpdated(title: String, favourite: Boolean) {
        animeRepository.updateFavourite(favourite, title)
    }
}
