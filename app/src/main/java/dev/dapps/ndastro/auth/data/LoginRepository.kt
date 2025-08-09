package dev.dapps.ndastro.auth.data

import dev.dapps.ndastro.data.network.NetworkResult

interface LoginRepository {
    suspend fun login(username: String, password: String): NetworkResult<LoginResponse>

    suspend fun user(): NetworkResult<LoginResponse>
}