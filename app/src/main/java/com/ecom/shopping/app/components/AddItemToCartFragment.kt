package com.ecom.shopping.app.components

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import com.ecom.shopping.R
import com.ecom.shopping.app.base.BaseDialogFragment
import com.ecom.shopping.app.utils.Utils
import com.ecom.shopping.app.utils.inflateLayout
import com.ecom.shopping.data.db.entities.CartItem
import com.ecom.shopping.data.db.entities.Item
import kotlinx.android.synthetic.main.dialog_add_item_to_cart.*
import kotlinx.android.synthetic.main.dialog_add_item_to_cart.view.*
import kotlinx.android.synthetic.main.layout_discount_with_name.view.*
import java.lang.RuntimeException
import java.util.*

class AddItemToCartFragment : BaseDialogFragment() {

    var currDiscount = 0.0f
    var currCheckedDiscount: Switch? = null
    var cartItem: CartItem? = null
    var item: Item? = null
    var isEdit = false
    val discountMap = HashMap<Float, Switch?>()

    lateinit var onAddCartItemSave: ShowAddItemToCartDialogListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is ShowAddItemToCartDialogListener) {
            onAddCartItemSave = context
        } else {
            throw RuntimeException("Must Implement ShowAddItemToCartDialogListener")
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v =  inflater.inflate(R.layout.dialog_add_item_to_cart,container,false)

        Utils.getDiscounts().forEach { discount ->
            val discountView = activity?.inflateLayout(R.layout.layout_discount_with_name, null, false)
            discountView?.rootView?.tv_discount_with_name?.text = "${discount.first} (${discount.second})"
            discountView?.rootView?.sw_discount?.setOnCheckedChangeListener { compoundButton, b ->
                if(b) {
                    currDiscount = discount.second
                    currCheckedDiscount?.isChecked = false
                    currCheckedDiscount = compoundButton as Switch
                } else {
                    currDiscount = 0.0f
                }

            }
            discountMap[discount.second] = discountView?.rootView?.sw_discount
            v.gl_discounts.addView(discountView)
        }
        isEdit = arguments?.getBoolean("is_edit") ?: false
        if(arguments?.containsKey("cart_item") == true) {
            cartItem = arguments?.getParcelable("cart_item")
        }
        if(arguments?.containsKey("item") == true) {
            item = arguments?.getParcelable("item")
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(cartItem != null) {
            view.et_quantity.setText((cartItem?.productItemCount ?: 0).toString())
            discountMap[cartItem?.discount]?.isChecked = true
            val itemName = if(cartItem?.item?.productName?.length ?: 0 > 25) cartItem?.item?.productName?.substring(0, 24) else cartItem?.item?.productName
            view.tv_add_item_header.text = "$itemName - $ ${(cartItem?.item?.price?: 0 * (cartItem?.productItemCount ?: 0))}"
        } else if(item != null) {
            view.et_quantity.setText("0")
            view.tv_add_item_header.text = "${item?.productName} - $ 0"
        }
        view.tv_save.setOnClickListener {
            if(cartItem == null && item != null) {
                cartItem = CartItem(null, et_quantity.text.toString().toInt(), currDiscount,item!!)
            } else {
                cartItem?.productItemCount = et_quantity.text.toString().toInt()
                cartItem?.discount = currDiscount
            }

            cartItem?.let {
                onAddCartItemSave.saveCartItem(it)
            }
            dismiss()
        }

        view.tv_cancel.setOnClickListener {
            dismiss()
        }
    }

    override fun getHeight(): Int {
        return ViewGroup.LayoutParams.WRAP_CONTENT
    }

    override fun getWidth(): Int {
        return ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun getBackgroundDrawableRes(): Int {
        return R.color.dark_translucent
    }

    companion object {
        fun getInstance(isEdit: Boolean, item: Item?, cartItem: CartItem? = null): AddItemToCartFragment {
            val addToCartFragment = AddItemToCartFragment()
            val bundle = Bundle()
            bundle.putBoolean("is_edit", isEdit)
            cartItem?.let {
                bundle.putParcelable("cart_item", it)
            }
            item?.let {
                bundle.putParcelable("item", item)
            }
            addToCartFragment.arguments = bundle
            return addToCartFragment
        }
    }

    interface ShowAddItemToCartDialogListener {
        fun saveCartItem(cartItem: CartItem)
    }
}