package dev.dapps.ndastro.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.secureDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

data class AuthToken(
    val accessToken: String?,
    val refreshToken: String?,
)

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.secureDataStore

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    suspend fun saveTokens(accessToken: String?, refreshToken: String?) {
        try {
            dataStore.edit { preferences ->
                accessToken?.let { preferences[ACCESS_TOKEN_KEY] = it }
                refreshToken?.let { preferences[REFRESH_TOKEN_KEY] = it }
            }
        } catch (e: IOException) {
            Log.e("TokenManager", "Error saving tokens: ${e.message}")
        }
    }

    val tokenData: Flow<AuthToken> = dataStore.data.map { preferences ->
        AuthToken(
            accessToken = preferences[ACCESS_TOKEN_KEY],
            refreshToken = preferences[REFRESH_TOKEN_KEY],
        )
    }

    suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }
}

