package com.ecom.shopping.domain.interactor

import com.ecom.shopping.domain.repository.ShoppingRepo
import io.reactivex.Single
import javax.inject.Inject

class ClearCartItemUseCase @Inject constructor(private val repository: ShoppingRepo): SingleUseCase<Any> {

    override fun execute(): Single<Any> {
        return repository.clearCart()
    }
}
