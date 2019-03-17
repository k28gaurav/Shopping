package com.ecom.shopping.domain.interactor

import com.ecom.shopping.data.db.entities.CartItem
import com.ecom.shopping.domain.repository.ShoppingRepo
import io.reactivex.Single
import javax.inject.Inject

class AddCartItemUseCase @Inject constructor(private val cartRepository: ShoppingRepo) {

    fun execute(cartItem: CartItem): Single<List<Long>> {
        return cartRepository.saveCartItem(cartItem)
    }
}