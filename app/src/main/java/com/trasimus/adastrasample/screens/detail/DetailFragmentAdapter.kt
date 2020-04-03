package com.trasimus.adastrasample.screens.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trasimus.adastrasample.R
import kotlinx.android.synthetic.main.detail_fragment_adapter_item_image.view.*
import kotlinx.android.synthetic.main.detail_fragment_adapter_item_info.view.*
import kotlinx.android.synthetic.main.detail_fragment_adapter_item_title.view.*

class DetailFragmentAdapter(val handler: DetailFragmentAdapterHandler) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val adapterItems: MutableList<BeerDetailAdapterItem> = mutableListOf()

    fun setData(newItems: MutableList<BeerDetailAdapterItem>) {
        this.adapterItems.clear()
        this.adapterItems.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rowType = RowType.values()[viewType]
        return rowType.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: BeerDetailAdapterItem = adapterItems[position]
        val rowType: RowType = RowType.values()[holder.itemViewType]
        rowType.onBindViewHolder(holder, item, this.handler)
    }

    override fun getItemCount() = this.adapterItems.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterItems[position]) {
            is BeerDetailAdapterItem.ItemImage -> RowType.IMAGE.ordinal
            is BeerDetailAdapterItem.ItemTitle -> RowType.TITLE.ordinal
            is BeerDetailAdapterItem.ItemInfo -> RowType.INFO.ordinal
        }
    }

    enum class RowType {
        IMAGE {
            override fun createViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
                val context: Context? = parent?.context
                val inflater: LayoutInflater = LayoutInflater.from(context)
                val view: View = inflater.inflate(R.layout.detail_fragment_adapter_item_image, parent, false)

                return BeerDetailViewHolder.ImageHolder(view)
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, adapterItem: BeerDetailAdapterItem, handler: DetailFragmentAdapterHandler) {
                (holder as? BeerDetailViewHolder.ImageHolder)?.let {
                    (adapterItem as? BeerDetailAdapterItem.ItemImage)?.let {
                        handler.inflateImage(adapterItem.url, holder.image, holder.loader)
                    }
                }
            }
        },
        TITLE {
            override fun createViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
                val context: Context? = parent?.context
                val inflater: LayoutInflater = LayoutInflater.from(context)
                val view: View = inflater.inflate(R.layout.detail_fragment_adapter_item_title, parent, false)

                return BeerDetailViewHolder.TitleHolder(view)
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, adapterItem: BeerDetailAdapterItem, handler: DetailFragmentAdapterHandler) {
                (holder as? BeerDetailViewHolder.TitleHolder)?.let {
                    (adapterItem as? BeerDetailAdapterItem.ItemTitle)?.let {
                        holder.title.text = adapterItem.title
                    }
                }
            }
        },
        INFO {
            override fun createViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
                val context: Context? = parent?.context
                val inflater: LayoutInflater = LayoutInflater.from(context)
                val view: View = inflater.inflate(R.layout.detail_fragment_adapter_item_info, parent, false)

                return BeerDetailViewHolder.InfoHolder(view)
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, adapterItem: BeerDetailAdapterItem, handler: DetailFragmentAdapterHandler) {
                (holder as? BeerDetailViewHolder.InfoHolder)?.let {
                    (adapterItem as? BeerDetailAdapterItem.ItemInfo)?.let {
                        holder.info.text = adapterItem.text
                    }
                }
            }
        };

        abstract fun createViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder
        abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, adapterItem: BeerDetailAdapterItem, handler: DetailFragmentAdapterHandler)
    }

    private sealed class BeerDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image: ImageView = itemView.detail_fragment_item_image
            val loader: ProgressBar = itemView.detail_fragment_item_loading
        }

        class TitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.detail_fragment_item_title
        }

        class InfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val info: TextView = itemView.detail_fragment_item_info
        }
    }

    sealed class BeerDetailAdapterItem {
        class ItemImage(val url: String) : BeerDetailAdapterItem()
        class ItemInfo(val text: String) : BeerDetailAdapterItem()
        class ItemTitle(val title: String) : BeerDetailAdapterItem()
    }
}