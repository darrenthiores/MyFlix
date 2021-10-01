package com.icebeal.myflix

import android.app.Application
import com.icebeal.core.di.databaseModule
import com.icebeal.core.di.networkModule
import com.icebeal.core.di.repositoryModule
import com.icebeal.myflix.di.useCaseModule
import com.icebeal.myflix.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {

            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )

        }

    }
}