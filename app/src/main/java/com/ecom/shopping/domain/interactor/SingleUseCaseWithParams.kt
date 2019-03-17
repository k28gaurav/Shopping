package com.ecom.shopping.domain.interactor

import io.reactivex.Single

interface SingleUseCaseWithParams<P, R> {
    fun execute(params:P): Single<R>
}