package org.sopt.and.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.response.SignInEntity

@Serializable
data class ResponseSignInDto(
    @SerialName("token")
    val token: String
){
    fun toEntity() = SignInEntity(
        token = token
    )
}
