package org.sopt.and.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.response.ResponseHobbyEntity

@Serializable
data class ResponseUserHobbyDto(
    @SerialName("hobby")
    val hobby: String
){
    fun toEntity() = ResponseHobbyEntity(
        hobby = hobby
    )
}
