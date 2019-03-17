package com.ecom.shopping.data.db.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ecom.shopping.data.db.entities.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(items: List<Item>): List<Long>

    @Query("Select * from ${Item.TABLE_NAME} where ${Item.PRODUCT_ID}=:productId")
    fun getProductItem(productId:Int):Item

    @Query("SELECT * FROM ${Item.TABLE_NAME}")
    fun getItems(): DataSource.Factory<Int, Item>

}