package com.ecom.shopping.data.db.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ecom.shopping.data.db.entities.CartItem

@Dao
interface CartItemDao {
    @Query("SELECT * FROM ${CartItem.TABLE_NAME}")
    fun getCartItems(): DataSource.Factory<Int, CartItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartItems(vararg items: CartItem): List<Long>

    @Query("DELETE FROM ${CartItem.TABLE_NAME}")
    fun clearCartItems()
}