package com.trasimus.adastrasample.screens.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trasimus.adastrasample.R
import com.trasimus.adastrasample.models.Beer
import kotlinx.android.synthetic.main.list_fragment_adapter_item.view.*

class ListFragmentAdapter(val handler: ListFragmentAdapterHandler): RecyclerView.Adapter<ListFragmentAdapter.ViewHolder>() {

    private val adapterItems: MutableList<Beer> = mutableListOf()

    fun setData(newItems: MutableList<Beer>) {
        this.adapterItems.clear()
        this.adapterItems.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return adapterItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_fragment_adapter_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = adapterItems[position]

        holder.itemName.text = beer.name
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var itemName: TextView = view.list_fragment_item_name
    }
}