package com.getzuper.challengeforzuper.di.component

import android.app.Application
import com.getzuper.challengeforzuper.MyApplication
import com.getzuper.challengeforzuper.di.module.ActivityModule
import com.getzuper.challengeforzuper.di.module.ApiModule
import com.getzuper.challengeforzuper.di.module.FragmentModule
import com.getzuper.challengeforzuper.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder


        fun build(): AppComponent
    }

    /*
     * This is our custom Application class
     * */
    fun inject(appController: MyApplication)


}