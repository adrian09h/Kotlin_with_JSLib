package com.getzuper.challengeforzuper

import android.app.Activity
import android.app.Application
import com.getzuper.challengeforzuper.di.component.AppComponent
import com.getzuper.challengeforzuper.di.component.DaggerAppComponent
import com.getzuper.challengeforzuper.di.module.ApiModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication: Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .apiModule(ApiModule())
            .build()
            .inject(this)
    }
}