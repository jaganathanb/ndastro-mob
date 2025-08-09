package dev.dapps.ndastro.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dapps.ndastro.BuildConfig
import dev.dapps.ndastro.data.network.http.NDAstroHttpClientBuilder
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesJson(): Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideHttpClient(builder: NDAstroHttpClientBuilder): HttpClient = builder
        .protocol(if (BuildConfig.DEBUG) URLProtocol.HTTP else URLProtocol.HTTPS )
        .host(BuildConfig.BASE_URL)
        .build()
}