package com.ecom.shopping.domain.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.ecom.shopping.data.db.entities.CartItem
import com.ecom.shopping.data.db.entities.Item
import com.ecom.shopping.data.models.Products
import io.reactivex.Single

interface ShoppingRepo {
    fun getItems(): LiveData<PagedList<Item>>

    fun saveCartItem(cartItem: CartItem): Single<List<Long>>

    fun getCartItems(): LiveData<PagedList<CartItem>>

    fun fetchItems(): Single<Products>

    fun saveAllItems(items: List<Item>)

    fun clearCart(): Single<Any>
}