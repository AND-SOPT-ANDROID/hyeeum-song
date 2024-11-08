package org.sopt.and.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.request.RequestSignInEntity

@Serializable
data class RequestSignInDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)

fun RequestSignInEntity.toDto() = RequestSignInDto(
    username = username,
    password = password
)
