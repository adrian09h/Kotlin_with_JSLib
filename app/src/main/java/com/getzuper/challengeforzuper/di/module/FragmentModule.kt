package com.getzuper.challengeforzuper.di.module

import com.getzuper.challengeforzuper.ui.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector()
    abstract fun contributeHomeFragment(): HomeFragment
}