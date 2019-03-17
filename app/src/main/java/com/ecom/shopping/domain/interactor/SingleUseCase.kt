package com.ecom.shopping.domain.interactor

import io.reactivex.Single

interface SingleUseCase<R> {
    fun execute(): Single<R>
}