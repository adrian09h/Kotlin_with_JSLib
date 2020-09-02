package com.getzuper.challengeforzuper.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.getzuper.challengeforzuper.factory.ViewModelFactory
import com.getzuper.challengeforzuper.factory.ViewModelKey
import com.getzuper.challengeforzuper.viewmodel.HomeFragViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragViewModel::class)
    protected abstract fun homeFragViewModel(homeFragViewModel: HomeFragViewModel): ViewModel


}