package com.ecom.shopping.domain.interactor

import com.ecom.shopping.data.db.entities.Item
import com.ecom.shopping.domain.repository.ShoppingRepo
import io.reactivex.Single
import javax.inject.Inject

class FetchItemListUseCase @Inject constructor(private val cartRepository: ShoppingRepo):SingleUseCase<List<Item>> {
    override fun execute(): Single<List<Item>> {
        return cartRepository.fetchItems()
    }
}