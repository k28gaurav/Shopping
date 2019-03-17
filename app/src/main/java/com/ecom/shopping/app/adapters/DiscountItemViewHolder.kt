package com.ecom.shopping.app.adapters

import android.view.View
import com.ecom.shopping.app.base.BaseViewHolder
import com.ecom.shopping.data.models.DiscountItem
import kotlinx.android.synthetic.main.item_discount.view.tv_discount_item_name
import kotlinx.android.synthetic.main.item_discount.view.tv_discount_value

class DiscountItemViewHolder (val view: View, val onItemClick: (position: Int) -> Unit): BaseViewHolder<DiscountItem>(view) {

    init {
        view.setOnClickListener {
           onItemClick.invoke(adapterPosition)
        }
    }


    override fun setData(data: DiscountItem) {
        view.tv_discount_item_name.text = data.title
        view.tv_discount_value.text = "${data.discountValue}"
    }

}