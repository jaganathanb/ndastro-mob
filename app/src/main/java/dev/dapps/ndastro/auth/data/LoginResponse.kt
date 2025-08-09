package dev.dapps.ndastro.auth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("username")
    val username: String? = null,
    @SerialName("access_token")
    val accessToken: Token? = null,
    @SerialName("refresh_token")
    val refreshToken: Token? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("email")
    val email: String? = null
)

data class User(
    val name: String?,
    val email: String?
)

fun LoginResponse.toUser(): User {
    return User(
        name = this.name,
        email = this.email
    )
}

@Serializable
data class Token(
    @SerialName("token")
    val token: String? = null,
    @SerialName("expires_in")
    val expiresIn: Int = 0,
    @SerialName("token_type")
    val tokenType: String = "bearer",
)