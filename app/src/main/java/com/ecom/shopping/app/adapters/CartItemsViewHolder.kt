package com.ecom.shopping.app.adapters

import android.view.View
import com.ecom.shopping.app.base.BaseViewHolder
import com.ecom.shopping.data.db.entities.CartItem
import kotlinx.android.synthetic.main.item_cart_adapter.view.*
import java.text.DecimalFormat

class CartItemsViewHolder (val view: View, onItemClick: (position: Int) -> Unit): BaseViewHolder<CartItem>(view) {

    init {
        view.setOnClickListener {
            onItemClick.invoke(adapterPosition)
        }
    }

    override fun setData(data: CartItem) {
        view.tv_cart_item_name.text = data.item.productName
        view.tv_cart_item_quantity.text = String.format("x%d", data.productItemCount)
        val decimalFormat = DecimalFormat("#,###,##0.00")
        view.tv_cart_item_price.text = "$ ${decimalFormat.format(data.item.price)}"
    }

}