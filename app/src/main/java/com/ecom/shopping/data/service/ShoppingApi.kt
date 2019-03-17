package com.ecom.shopping.data.service

import com.ecom.shopping.data.db.entities.Item
import io.reactivex.Single
import retrofit2.http.GET

interface ShoppingApi {
    @GET("/bins/i5qto")
    fun getAllItems(): Single<List<Item>>
}