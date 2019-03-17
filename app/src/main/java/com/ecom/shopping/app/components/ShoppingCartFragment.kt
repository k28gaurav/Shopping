package com.ecom.shopping.app.components

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecom.shopping.R
import com.ecom.shopping.app.adapters.CartItemsAdapter
import com.ecom.shopping.app.base.DaggerBaseFragment
import com.ecom.shopping.data.db.entities.CartItem
import com.ecom.shopping.viewmodels.ShoppingCartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.layout_fragment_header.*
import java.lang.RuntimeException
import java.text.DecimalFormat
import javax.inject.Inject

class ShoppingCartFragment : DaggerBaseFragment<ShoppingCartViewModel>() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var cartItemsAdapter: CartItemsAdapter
    lateinit var showEditItemToCartDialogListener: ShowEditItemToCartDialogListener

    private var subtotal = 0.0f
    private var discount = 0.0f

    companion object {
        fun newInstance(): ShoppingCartFragment {
            return ShoppingCartFragment()
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity?.let {
            viewModel = ViewModelProviders.of(it, viewModelFactory)[ShoppingCartViewModel::class.java]
        }

        if (context is ShowEditItemToCartDialogListener) {
            showEditItemToCartDialogListener = context
        } else {
            throw RuntimeException("Must Implement OnClickDiscountItems")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


    override fun initEventHandlers() {
        bt_clear_sale.setOnClickListener(this)
    }

    override fun initViews() {
        tv_fragment_header.text = "SHOPPING CART"
        rv_cart_items.layoutManager = LinearLayoutManager(activity)
        activity?.let {
            cartItemsAdapter = CartItemsAdapter(it)  { position, item ->
                item?.let {
                    showEditItemToCartDialogListener.onShowEditItemToCartDialog(item)
                }

            }
            rv_cart_items.adapter = cartItemsAdapter

        }
        super.initViews()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            bt_clear_sale.id -> {
                viewModel.clearCartItems()
            }
        }
    }

    override fun observeViewModel() {
        viewModel.cartItemsLiveData.observe(this, Observer { cartItems ->
            cartItems?.let {
                if(it.size == 0) {
                    subtotal = 0.0f
                    discount = 0.0f
                }
                it.forEach {cartItem ->
                    val price = (cartItem.productItemCount * cartItem.item.price)
                    subtotal += price
                    discount += price * cartItem.discount / 100
                }
                cartItemsAdapter.submitList(it)
                val decimalFormat = DecimalFormat("#,###,##0.00")
                tv_subtotal.text = "$ ${decimalFormat.format(subtotal)}"
                tv_discount.text = "$ ${decimalFormat.format(discount)}"
                tv_charge.text = "$ ${decimalFormat.format(subtotal - discount)}"
            }
        })

    }

    interface ShowEditItemToCartDialogListener {
        fun onShowEditItemToCartDialog(item: CartItem)
    }
}