package com.ecom.shopping.data.service

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.ecom.shopping.app.rx.SchedulersFacade
import com.ecom.shopping.data.db.ShoppingDatabase
import com.ecom.shopping.data.db.entities.CartItem
import com.ecom.shopping.data.db.entities.Item
import com.ecom.shopping.domain.repository.ShoppingRepo
import io.reactivex.Single
import javax.inject.Inject

class ShoppingRepoImpl @Inject constructor(private val cartDatabase: ShoppingDatabase,
                                           private val cartApiService: ShoppingApi,
                                           private val schedulersFacade: SchedulersFacade
): ShoppingRepo {

    override fun saveAllItems(items: List<Item>) {
        cartDatabase.itemDao().insertItems(items)
    }

    override fun getItems(): LiveData<PagedList<Item>> {
        return LivePagedListBuilder(cartDatabase.itemDao().getItems(), 20).build()
    }

    override fun fetchItems(): Single<List<Item>> {
        return cartApiService.getAllItems()
    }

    override fun saveCartItem(cartItem: CartItem): Single<List<Long>> {
        return Single.create<List<Long>> { subscriber ->
            val res = cartDatabase.cartItemDao().insertCartItems(cartItem)
            subscriber.onSuccess(res)
        }

    }

    override fun getCartItems(): LiveData<PagedList<CartItem>> {
        return LivePagedListBuilder(cartDatabase.cartItemDao().getCartItems(), 20).build()
    }

    override fun clearCart(): Single<Any> {
        return Single.create<Any> { subscriber ->
            val res = cartDatabase.cartItemDao().clearCartItems()
            subscriber.onSuccess("lol")
        }
    }
}