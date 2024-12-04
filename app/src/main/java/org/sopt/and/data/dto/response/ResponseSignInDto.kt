package org.sopt.and.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.response.ResponseSignInEntity

@Serializable
data class ResponseSignInDto(
    @SerialName("token")
    val token: String
){
    fun toEntity() = ResponseSignInEntity(
        token = token
    )
}
