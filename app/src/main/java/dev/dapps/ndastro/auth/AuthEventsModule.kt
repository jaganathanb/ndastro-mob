package dev.dapps.ndastro.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.dapps.ndastro.auth.ui.LoginNavigationEvents
import dev.dapps.ndastro.commons.ViewModelEvents
import dev.dapps.ndastro.commons.ViewModelEventsImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class AuthEventsModule {
    @Binds
    abstract fun bindLoginViewModelEvents(
        impl: ViewModelEventsImpl<LoginNavigationEvents>,
    ): ViewModelEvents<LoginNavigationEvents>
}