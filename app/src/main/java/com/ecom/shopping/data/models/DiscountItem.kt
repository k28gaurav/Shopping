package com.ecom.shopping.data.models

import com.google.gson.annotations.SerializedName

data class DiscountItem(@SerializedName("title") val title:String,
                        @SerializedName("discount_value") val discountValue: Double)
