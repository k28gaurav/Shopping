package com.ecom.shopping.app.di

import android.app.Application
import android.content.Context
import com.ecom.shopping.app.rx.SchedulerProvider
import com.ecom.shopping.app.rx.SchedulersFacade
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(appliction: Application): Context

    @Binds
    abstract fun providerScheduler(schedulersFacade: SchedulersFacade): SchedulerProvider
}