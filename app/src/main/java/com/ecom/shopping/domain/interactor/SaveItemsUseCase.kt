package com.ecom.shopping.domain.interactor

import com.ecom.shopping.data.db.entities.Item
import com.ecom.shopping.domain.repository.ShoppingRepo
import javax.inject.Inject

class SaveItemsUseCase @Inject constructor(private val cartRepository: ShoppingRepo) {
    fun execute(items: List<Item>) {
        cartRepository.saveAllItems(items)
    }
}