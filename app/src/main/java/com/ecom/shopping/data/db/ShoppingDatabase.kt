package com.ecom.shopping.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ecom.shopping.data.db.dao.CartItemDao
import com.ecom.shopping.data.db.dao.ItemDao
import com.ecom.shopping.data.db.entities.CartItem
import com.ecom.shopping.data.db.entities.Item

@Database(entities = [Item::class, CartItem::class], version = 1)
abstract class ShoppingDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun cartItemDao(): CartItemDao
}