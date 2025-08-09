package dev.dapps.ndastro.home

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.dapps.ndastro.commons.ViewModelEvents
import dev.dapps.ndastro.commons.ViewModelEventsImpl
import dev.dapps.ndastro.home.ui.HomeNavigationEvents

@InstallIn(ViewModelComponent::class)
@Module
abstract class HomeEventsModule {
    @Binds
    abstract fun bindHomeViewModelEvents(
        impl: ViewModelEventsImpl<HomeNavigationEvents>,
    ): ViewModelEvents<HomeNavigationEvents>
}