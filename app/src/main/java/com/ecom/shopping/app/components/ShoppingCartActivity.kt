package com.ecom.shopping.app.components

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.ecom.shopping.R
import com.ecom.shopping.app.base.DaggerBaseActivity
import com.ecom.shopping.data.db.entities.CartItem
import com.ecom.shopping.data.db.entities.Item
import com.ecom.shopping.data.models.DiscountItem
import com.ecom.shopping.viewmodels.ShoppingCartViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ShoppingCartActivity : DaggerBaseActivity<ShoppingCartViewModel>(),LibraryFragment.ChangeFragmentListener,
    AllItemsFragment.ShowAddItemToCartDialogListener, AddItemToCartFragment.ShowAddItemToCartDialogListener,
    ShoppingCartFragment.ShowEditItemToCartDialogListener, DiscountFragment.OnClickDiscountItems {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject lateinit var libraryFragment: LibraryFragment
    @Inject lateinit var cartFragment: ShoppingCartFragment
    @Inject lateinit var allItemsFragment: AllItemsFragment
    @Inject lateinit var discountFragment: DiscountFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ShoppingCartViewModel::class.java]
        initViews()
    }

     fun initViews() {
        supportFragmentManager?.beginTransaction()
            ?.add(fl_library_container.id, libraryFragment, "library")
            ?.add(fl_cart_container.id, cartFragment, "cart")
            ?.commit()
    }

    override fun onFragmentChange(fragmentName: LibraryFragment.NextFrag) {
        when(fragmentName) {
            LibraryFragment.NextFrag.ALL_ITEMS -> {
                supportFragmentManager?.beginTransaction()
                    ?.replace(fl_library_container.id, allItemsFragment, "allItems")
                    ?.addToBackStack(null)
                    ?.commit()
            }

            LibraryFragment.NextFrag.ALL_DISCOUNT -> {
                supportFragmentManager?.beginTransaction()
                    ?.replace(fl_library_container.id, discountFragment, "discountItems")
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
    }

    override fun saveCartItem(cartItem: CartItem) {
        viewModel.saveCartItem(cartItem)
    }

    override fun onShowAddItemToCartDialog(item: Item) {

        AddItemToCartFragment.getInstance(false, item, null).show(supportFragmentManager, "")

    }

    override fun onShowEditItemToCartDialog(item: CartItem) {
        AddItemToCartFragment.getInstance(true, null, item).show(supportFragmentManager, "")

    }

    override fun addDiscountToCart(item: DiscountItem) {

    }

}
