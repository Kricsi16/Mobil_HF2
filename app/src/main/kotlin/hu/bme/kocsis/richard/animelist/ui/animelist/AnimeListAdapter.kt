package hu.bme.kocsis.richard.animelist.ui.animelist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import hu.bme.kocsis.richard.animelist.R
import hu.bme.kocsis.richard.animelist.model.AnimeDto
import hu.bme.kocsis.richard.animelist.ui.details.DetailsActivity

class AnimeListAdapter constructor(
        private val context: Context,
        private var animeList: List<AnimeDto>,
        private val listener: AnimeFavouriteListener
) : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {

    interface AnimeFavouriteListener {
        fun onFavouriteUpdated(title: String, favourite: Boolean)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AnimeViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.anime_item, viewGroup, false)
        return AnimeViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val item = animeList[position]
        holder.title.text = item.title
        val favouriteButton = holder.favouriteButton
        holder.favourite = item.favourite

        if (item.favourite)
            favouriteButton.setImageDrawable(ContextCompat.getDrawable(favouriteButton.context, android.R.drawable.btn_star_big_on))
        else
            favouriteButton.setImageDrawable(ContextCompat.getDrawable(favouriteButton.context, android.R.drawable.btn_star_big_off))


    }

    override fun getItemCount() = animeList.size

    class AnimeViewHolder(view: View, listener: AnimeFavouriteListener) : RecyclerView.ViewHolder(view) {
        var title: TextView = itemView.findViewById(R.id.AnimeTitle)
        var detailsButton: Button = itemView.findViewById(R.id.DetailsButton)
        var favouriteButton: ImageButton = itemView.findViewById(R.id.FavouriteImageButton)
        var favourite: Boolean = false

        init {
            detailsButton.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                intent.putExtra("Title", title.text)

                itemView.context.startActivity(intent)
            }

            favouriteButton.setOnClickListener {
                favourite = !favourite
                val d = if (favourite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
                favouriteButton.setImageDrawable(ContextCompat.getDrawable(favouriteButton.context, d))

                listener.onFavouriteUpdated(title.text.toString(), favourite)
            }
        }
    }
}
