package com.ecom.shopping.data.di

import com.ecom.shopping.data.service.ShoppingRepoImpl
import com.ecom.shopping.domain.repository.ShoppingRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideCartRepository(shoppingRepoImpl: ShoppingRepoImpl): ShoppingRepo

}