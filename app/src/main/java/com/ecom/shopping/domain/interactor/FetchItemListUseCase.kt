package com.ecom.shopping.domain.interactor

import com.ecom.shopping.data.db.entities.Item
import com.ecom.shopping.data.models.Products
import com.ecom.shopping.domain.repository.ShoppingRepo
import io.reactivex.Single
import javax.inject.Inject

class FetchItemListUseCase @Inject constructor(private val cartRepository: ShoppingRepo):SingleUseCase<Products> {
    override fun execute(): Single<Products> {
        return cartRepository.fetchItems()
    }
}