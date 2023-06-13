package com.leonel.intercamlp.adapter

import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color.red
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.leonel.intercamlp.R
import com.leonel.intercamlp.model.Beer
import com.leonel.intercamlp.model.favorito
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.coroutines.NonDisposableHandle.parent

class FavoritesAdapter (private val mList: List<favorito>): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_beer_saved, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.txt_title.text = ItemsViewModel.name
        holder.txt_subtitle.text = ItemsViewModel.tagline

        Picasso.get()
            .load(ItemsViewModel.image)
            .into(holder.img_beer)

        holder.ratingBar.rating = ItemsViewModel.rating.toFloat()

        holder.ratingBar.setOnRatingBarChangeListener { ratingBar, stars, b ->
            val estrellas = stars.toInt()
            var updaterating= ItemsViewModel
            updaterating.rating= estrellas
            if (onClickListener != null) {
                onClickListener!!.onClick(position, updaterating)
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, updateFavorite: favorito)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txt_title: TextView = itemView.findViewById(R.id.txt_titleBeersaved)
        val txt_subtitle: TextView = itemView.findViewById(R.id.txt_subtitleBersaved)
        val img_beer: ImageView = itemView.findViewById(R.id.img_beer)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)


    }
}