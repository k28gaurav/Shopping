package com.ecom.shopping.app.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ecom.shopping.app.base.BaseViewHolder
import com.ecom.shopping.data.db.entities.Item
import kotlinx.android.synthetic.main.item_product.view.*
import java.text.DecimalFormat

class AllItemsViewHolder(val view: View, val onClick : (position:Int) -> Unit): BaseViewHolder<Item>(view) {

    init {
        view.setOnClickListener {
            onClick.invoke(adapterPosition)
        }
    }

    override fun setData(data: Item) {
        view.tv_product_name.text = data.productName
        Glide.with(view.context)
            .load(data.productUrl)
            .apply(RequestOptions().centerCrop())
            .into(view.iv_product_item)
        val decimalFormat = DecimalFormat("#,###,##0.00")
        view.tv_product_price.text = "$ ${decimalFormat.format(data.price)}"
        view.rb_product_rating.rating = data.productRating
    }
}