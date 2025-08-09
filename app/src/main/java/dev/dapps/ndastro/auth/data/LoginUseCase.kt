package dev.dapps.ndastro.auth.data

import dev.dapps.ndastro.data.TokenManager
import dev.dapps.ndastro.data.network.NetworkResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val tokenManager: TokenManager,
) {
    suspend operator fun invoke(username: String, password: String): NetworkResult<LoginResponse> {
        val result = repository.login(username, password)
        if (result is NetworkResult.Success) {
            tokenManager.saveTokens(
                result.data.accessToken?.token,
                result.data.refreshToken?.token,
            )
        }
        return result
    }
}
