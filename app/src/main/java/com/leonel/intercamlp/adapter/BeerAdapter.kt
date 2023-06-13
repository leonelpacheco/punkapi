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
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.coroutines.NonDisposableHandle.parent

class BeerAdapter (private val mList: List<Beer>): RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null
    private var onClickListenerFavorite: OnClickListener? = null
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_beer, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.txt_title.text = ItemsViewModel.name
        holder.txt_subtitle.text = ItemsViewModel.tagline
        Picasso.get()
            .load(ItemsViewModel.imageURL)
            .into(holder.img_beer)

        holder.btn_favorite.setOnClickListener {

            if (onClickListener != null) {
                onClickListener!!.onClickFavorite(position, ItemsViewModel)
            }
            holder.btn_favorite.backgroundTintList= ColorStateList.valueOf(ContextCompat.getColor(context, R.color.purple_500))
        }

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, ItemsViewModel)
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: Beer)
        fun onClickFavorite(position: Int, model: Beer)
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txt_title: TextView = itemView.findViewById(R.id.txt_title)
        val txt_subtitle: TextView = itemView.findViewById(R.id.txt_subtitle)
        val img_beer: ImageView = itemView.findViewById(R.id.img_beer)
        val btn_favorite: ImageButton = itemView.findViewById(R.id.btn_favorite)


    }
}