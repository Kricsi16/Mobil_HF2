package hu.bme.kocsis.richard.animelist.ui.favourites

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.kocsis.richard.animelist.R
import hu.bme.kocsis.richard.animelist.model.AnimeDto
import hu.bme.kocsis.richard.animelist.ui.details.DetailsActivity

class FavouritesAdapter constructor(
        private val context: Context,
        private val favouritesList: MutableList<AnimeDto> = mutableListOf()

) : RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FavouritesViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.favourite_item, viewGroup, false)
        return FavouritesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val item = favouritesList[position]
        holder.title.text = item.title

    }

    fun initList(list: List<AnimeDto>){
        favouritesList.clear()
        favouritesList.addAll(list)

    }

    override fun getItemCount() = favouritesList.size

    class FavouritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = itemView.findViewById(R.id.AnimeTitle)
        var detailsButton: Button = itemView.findViewById(R.id.DetailsButton)

        init {
            detailsButton.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                intent.putExtra("Title", title.text)

                itemView.context.startActivity(intent)
            }

        }
    }
}
