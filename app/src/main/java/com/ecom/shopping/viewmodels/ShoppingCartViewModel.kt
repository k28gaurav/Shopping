package com.ecom.shopping.viewmodels

import com.ecom.shopping.app.base.BaseViewModel
import com.ecom.shopping.app.rx.SchedulersFacade
import com.ecom.shopping.data.db.entities.CartItem
import com.ecom.shopping.data.models.DiscountItem
import com.ecom.shopping.domain.interactor.*
import timber.log.Timber
import javax.inject.Inject

class ShoppingCartViewModel @Inject constructor(schedulersFacade: SchedulersFacade,
                                                getItemsUseCase: GetItemsUseCase,
                                                private val fetchItemListUseCase: FetchItemListUseCase,
                                                private val saveItemsUseCase: SaveItemsUseCase,
                                                private val clearCartItemUseCase: ClearCartItemUseCase,
                                                private val getCartItemsUseCase: GetCartItemUseCase,
                                                private val addCartItemUseCase: AddCartItemUseCase
):
    BaseViewModel(schedulersFacade) {

    val itemsLiveData = getItemsUseCase.execute()
    val cartItemsLiveData = getCartItemsUseCase.execute()

    fun fetchItemList() {
        val fetchItemListDisposal = fetchItemListUseCase.execute()
            .subscribeOn(schedulers.io())
            .map { items ->
                saveItemsUseCase.execute(items)
            }
            .observeOn(schedulers.ui())
            .subscribe({

            }, { err ->
                Timber.e(err)
                //TODO: Show error  toast and stop loading
            })

        getCompositeDisposable().add(fetchItemListDisposal)
    }

    fun clearCartItems() {
        clearCartItemUseCase.execute()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe()
    }

    fun saveCartItem(cartItem: CartItem) {
        addCartItemUseCase.execute(cartItem)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe()
    }

    fun getDiscountList():MutableList<DiscountItem> {
        val discountList = mutableListOf<DiscountItem>()
        discountList.add(DiscountItem("Discount A", 0.0))
        discountList.add(DiscountItem("Discount B", 10.0))
        discountList.add(DiscountItem("Discount C", 35.5))
        discountList.add(DiscountItem("Discount D", 50.0))
        discountList.add(DiscountItem("Discount E", 100.0))

        return discountList
    }
}