package org.sopt.and.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.and.domain.entity.response.HobbyEntity

@Serializable
data class ResponseHobbyDto(
    @SerialName("hobby")
    val hobby: String
){
    fun toEntity() = HobbyEntity(
        hobby = hobby
    )
}
