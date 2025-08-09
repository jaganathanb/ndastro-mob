package dev.dapps.ndastro.auth.data

import dev.dapps.ndastro.data.network.NetworkResult
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(): NetworkResult<LoginResponse> = repository.user()
}
