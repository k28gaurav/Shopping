package com.ecom.shopping.data.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Item.TABLE_NAME)
data class Item(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @ColumnInfo(name= PRODUCT_ID)
    val productId:Int,

    @SerializedName("name")
    @ColumnInfo(name = PRODUCT_NAME)
    val productName: String,

    @SerializedName("price")
    @ColumnInfo(name= PRODUCT_PRICE)
    val productPrice: String,

    @SerializedName("image_url")
    @ColumnInfo(name= IMAGE_URL)
    val productUrl:String,

    @SerializedName("rating")
    @ColumnInfo(name = PRODUCT_RATING)
    val productRating:Float
):Parcelable {

    companion object {
        const val TABLE_NAME = "product"
        const val PRODUCT_ID = "product_id"
        const val PRODUCT_NAME = "product_name"
        const val PRODUCT_PRICE = "product_price"
        const val IMAGE_URL = "image_url"
        const val PRODUCT_RATING = "product_rating"
    }

    val price: Float
        get() {
            return productPrice.toFloat()
        }

}