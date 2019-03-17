package com.ecom.shopping.app.components

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecom.shopping.R
import com.ecom.shopping.app.adapters.DiscountItemsAdapter
import com.ecom.shopping.app.base.DaggerBaseFragment
import com.ecom.shopping.data.models.DiscountItem
import com.ecom.shopping.viewmodels.ShoppingCartViewModel
import kotlinx.android.synthetic.main.fragment_discount.*
import kotlinx.android.synthetic.main.layout_fragment_header.*
import java.lang.RuntimeException
import javax.inject.Inject

class DiscountFragment @Inject constructor() : DaggerBaseFragment<ShoppingCartViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    lateinit var discountItemAdapter: DiscountItemsAdapter
    lateinit var onClickDiscountItem: OnClickDiscountItems


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discount, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)[ShoppingCartViewModel::class.java]

        if (context is OnClickDiscountItems) {
            onClickDiscountItem = context
        } else {
            throw RuntimeException("Must Implement OnClickDiscountItems")
        }
    }

    override fun initViews() {
        tv_fragment_header.text = "DISCOUNT"
        rv_discount_items.layoutManager = LinearLayoutManager(activity)
        activity?.let {
            discountItemAdapter = DiscountItemsAdapter(it) { position, item ->
                onClickDiscountItem.addDiscountToCart(item)
            }
            rv_discount_items.adapter = discountItemAdapter
        }
        super.initViews()
    }


    override fun initEventHandlers() {

    }

    override fun observeViewModel() {
        discountItemAdapter?.discountItems = viewModel.getDiscountList()
    }

    override fun onClick(view: View?) {
        when (view?.id) {

        }
    }

    interface OnClickDiscountItems {
        fun addDiscountToCart(item: DiscountItem)
    }
}