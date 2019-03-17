package com.ecom.shopping.data.db.entities

import android.arch.persistence.room.*
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = CartItem.TABLE_NAME, indices = [Index(value = [Item.PRODUCT_ID], unique = true)])
data class CartItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= CHECKOUT_ID)
    val checkoutId:Int?,

    @ColumnInfo(name = PRODUCT_ITEM_COUNT)
    var productItemCount:Int,

    @ColumnInfo(name = DISCOUNT)
    var discount: Float,

    @Embedded
    val item: Item
):Parcelable {
    companion object {
        const val TABLE_NAME = "checkout"
        const val PRODUCT_ITEM_COUNT = "product_item_count"
        const val CHECKOUT_ID ="checkout_id"
        const val DISCOUNT = "discount"
    }
}