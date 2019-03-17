package com.ecom.shopping.data.di

import android.arch.persistence.room.Room
import android.content.Context
import com.ecom.shopping.app.utils.Constants
import com.ecom.shopping.data.db.ShoppingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCartDatabase(context: Context): ShoppingDatabase {
        return Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java,
            Constants.DB_NAME).build()
    }
}