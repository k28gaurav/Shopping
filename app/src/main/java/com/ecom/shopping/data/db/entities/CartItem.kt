package com.ecom.shopping.data.db.entities

import android.arch.persistence.room.*

@Entity(tableName = CartItem.TABLE_NAME, indices = [Index(value = [CartItem.PRODUCT_ID], unique = true)])
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= CHECKOUT_ID)
    val checkoutId:Int,

    @ColumnInfo(name = PRODUCT_ID)
    var productId:Int,

    @ColumnInfo(name = PRODUCT_ITEM_COUNT)
    var productItemCount:Int,

    @Embedded
    val item: Item
) {
    companion object {
        const val TABLE_NAME = "checkout"
        const val PRODUCT_ID = "product_id"
        const val PRODUCT_ITEM_COUNT = "product_item_count"
        const val TOTAL_AMOUNT = "total_amount"
        const val CHECKOUT_ID ="checkout_id"
    }
}