package com.ecom.shopping.app.adapters

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.ecom.shopping.R
import com.ecom.shopping.app.utils.inflateLayout
import com.ecom.shopping.data.db.entities.Item


class AllItemsAdapter(context: Context, val onItemClick: (position: Int, item: Item) -> Unit): PagedListAdapter<Item, AllItemsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllItemsViewHolder {
        val view = parent.context.inflateLayout(R.layout.item_product, parent, false)
        return AllItemsViewHolder(view) { position ->
            val item = getItem(position)
            item?.let {
                onItemClick.invoke(position, it)
            }

        }
    }

    override fun onBindViewHolder(holder: AllItemsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.setData(it)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item,
                                         newItem: Item): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item,
                                            newItem: Item): Boolean =
                oldItem == newItem
        }
    }
}