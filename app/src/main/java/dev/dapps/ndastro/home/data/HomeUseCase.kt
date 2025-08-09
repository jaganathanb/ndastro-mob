package dev.dapps.ndastro.home.data

import dev.dapps.ndastro.data.TokenManager
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val tokenManager: TokenManager,
) {
    suspend fun logout() {
        tokenManager.clearTokens()
    }
}