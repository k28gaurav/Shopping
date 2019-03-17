package com.ecom.shopping.domain.interactor

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.ecom.shopping.data.db.entities.Item
import com.ecom.shopping.domain.repository.ShoppingRepo
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val cartRepository: ShoppingRepo){
    fun execute(): LiveData<PagedList<Item>> {
        return cartRepository.getItems()
    }
}