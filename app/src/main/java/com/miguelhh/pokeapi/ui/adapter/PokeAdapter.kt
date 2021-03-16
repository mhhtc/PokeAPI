package com.miguelhh.pokeapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miguelhh.pokeapi.R
import com.miguelhh.pokeapi.model.PokeItem
import com.miguelhh.pokeapi.utils.URL_IMAGES_LIST
import kotlinx.android.synthetic.main.item_poke.view.*

class PokeAdapter( private val interaction: Interaction? = null) : RecyclerView.Adapter<PokeAdapter.PokeViewHolder?>() {

    private var pokeList = mutableListOf<PokeItem>()

    fun setPokeList(data:MutableList<PokeItem>){
        pokeList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_poke, parent, false)
        return PokeViewHolder(view, interaction)
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        val pokeItem: PokeItem = pokeList[position]
        holder.bindView(pokeItem)
    }

    override fun getItemCount(): Int {
        return if(pokeList.size > 0){ pokeList.size}else{ 0 }
    }

    inner class PokeViewHolder internal constructor(
        itemView: View,
        private val interaction: Interaction?) : RecyclerView.ViewHolder(itemView) {
        private lateinit var pokeItem: PokeItem

        fun bindView(pokeItem: PokeItem) {
            this.pokeItem = pokeItem
            itemView.poke_name.text = pokeItem.name.capitalize()

            Glide.with(itemView)
                .load(URL_IMAGES_LIST + pokeItem.getId().toString() + ".png")
                .centerInside()
                .into(itemView.poke_image)
        }

        init {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, pokeItem)
            }
        }
    }

}


interface Interaction {
    fun onItemSelected(position: Int, item: PokeItem)
}