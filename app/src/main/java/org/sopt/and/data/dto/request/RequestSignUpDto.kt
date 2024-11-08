package org.sopt.and.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.request.RequestSignUpEntity

@Serializable
data class RequestSignUpDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("hobby")
    val hobby: String,
)

fun RequestSignUpEntity.toDto() = RequestSignUpDto(
    username = username,
    password = password,
    hobby = hobby
)

