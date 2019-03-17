package com.ecom.shopping.app.base

interface AdapterItemClick<T> {

    fun onItemClick(viewType: Int, data:T)
}