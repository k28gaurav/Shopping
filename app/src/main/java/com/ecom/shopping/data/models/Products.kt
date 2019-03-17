package com.ecom.shopping.data.models

import com.ecom.shopping.data.db.entities.Item
import com.google.gson.annotations.SerializedName


data class Products(
@SerializedName("products") val products: List<Item>
)