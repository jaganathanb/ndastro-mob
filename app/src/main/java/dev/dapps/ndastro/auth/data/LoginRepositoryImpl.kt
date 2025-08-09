package dev.dapps.ndastro.auth.data

import dev.dapps.ndastro.data.network.NetworkResult
import dev.dapps.ndastro.data.network.http.NetworkClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.http.Parameters
import kotlinx.serialization.Serializable
import javax.inject.Inject

private const val URL_LOGIN = "/auth/login"
private const val URL_USER = "/auth/me"

class LoginRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
) : LoginRepository {

    override suspend fun login(
        username: String,
        password: String,
    ): NetworkResult<LoginResponse> = networkClient.post(
        url = URL_LOGIN,
        body = LoginRequest(username, password).toFormData(),
        headers = mapOf("Content-Type" to "application/x-www-form-urlencoded"),
        responseSerializer = LoginResponse.serializer(),
    )

    override suspend fun user(): NetworkResult<LoginResponse> = networkClient.get<LoginResponse>(
        url = URL_USER,
        responseSerializer = LoginResponse.serializer(),
    )
}

@Serializable
data class LoginRequest(
    val username: String,
    val password: String,
    val grantType: String = "password"
)

fun LoginRequest.toFormData(): FormDataContent {
    return FormDataContent(Parameters.build {
        append("username", username)
        append("password", password)
        append("grant_type", grantType)
    })
}

