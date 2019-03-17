package com.ecom.shopping.app.utils

class Utils {

    companion object {
        val discount = arrayListOf<Pair<String, Float>>()

        fun getDiscounts(): List<Pair<String, Float>> {
            if(discount.isEmpty()) {
                discount.add(Pair("Discount A", 0f))
                discount.add(Pair("Discount B", 10f))
                discount.add(Pair("Discount C", 35.5f))
                discount.add(Pair("Discount D", 50.0f))
                discount.add(Pair("Discount E", 100.0f))
            }
            return discount
        }
    }
}