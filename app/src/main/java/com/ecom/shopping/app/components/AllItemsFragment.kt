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
import com.ecom.shopping.app.adapters.AllItemsAdapter
import com.ecom.shopping.app.base.DaggerBaseFragment
import com.ecom.shopping.data.db.entities.Item
import com.ecom.shopping.viewmodels.ShoppingCartViewModel
import kotlinx.android.synthetic.main.fragment_product_item_list.*
import kotlinx.android.synthetic.main.layout_fragment_header.*
import java.lang.RuntimeException
import javax.inject.Inject

class AllItemsFragment : DaggerBaseFragment<ShoppingCartViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    lateinit var allItemsAdapter: AllItemsAdapter
    lateinit var showAddItemToCartDialogListener: ShowAddItemToCartDialogListener

    companion object {
        fun newInstance(): AllItemsFragment {
            return AllItemsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_item_list, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ShoppingCartViewModel::class.java]
        if(context is ShowAddItemToCartDialogListener) {
            showAddItemToCartDialogListener = context
        } else {
            throw RuntimeException("Must Implement ShowAddItemToCartDialogListener")
        }
    }

    override fun initViews() {
        tv_fragment_header.text = "ALL ITEMS"
        rv_all_items.layoutManager = LinearLayoutManager(activity)
        activity?.let {
            allItemsAdapter = AllItemsAdapter(it) { position, item ->
                showAddItemToCartDialog(item)
            }
            rv_all_items.adapter = allItemsAdapter
        }
        super.initViews()
    }

    private fun showAddItemToCartDialog(item: Item) {
        showAddItemToCartDialogListener.onShowAddItemToCartDialog(item)
    }


    override fun observeViewModel() {
        viewModel.itemsLiveData.observe(this, Observer { items ->
            items?.let {
                if(it.size == 0) {
                    viewModel.fetchItemList()
                }
                allItemsAdapter.submitList(items)
            }
        })
    }

    override fun onClick(view: View?) {
        when(view?.id) {

        }
    }

    interface ShowAddItemToCartDialogListener {
        fun onShowAddItemToCartDialog(item: Item)
    }
}