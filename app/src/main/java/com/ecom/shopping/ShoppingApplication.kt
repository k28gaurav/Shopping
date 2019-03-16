package com.ecom.shopping

import com.ecom.shopping.app.di.AppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import com.ecom.shopping.app.di.DaggerAppComponent
import timber.log.Timber

class ShoppingApplication: DaggerApplication() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var app: ShoppingApplication
            internal set
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent =  DaggerAppComponent.builder().application(this).build()
        return appComponent
    }
}