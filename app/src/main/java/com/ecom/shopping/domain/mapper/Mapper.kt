package com.ecom.shopping.domain.mapper

interface Mapper<out R, in P>{
    fun mapEntity(model:P): R
}